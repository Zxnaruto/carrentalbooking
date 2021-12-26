package com.zhaixin.carrentalbooking.service.impl;

import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.constant.ResultCode;
import com.zhaixin.carrentalbooking.dao.CarInfoDao;
import com.zhaixin.carrentalbooking.dao.CarRentalOrderDao;
import com.zhaixin.carrentalbooking.dao.UserDao;
import com.zhaixin.carrentalbooking.dto.CarRentalOrderDTO;
import com.zhaixin.carrentalbooking.dto.UserDTO;
import com.zhaixin.carrentalbooking.entity.CarRentalOrder;
import com.zhaixin.carrentalbooking.entity.User;
import com.zhaixin.carrentalbooking.service.CarInfoService;
import com.zhaixin.carrentalbooking.service.CarRentalOrderService;
import com.zhaixin.carrentalbooking.service.UserService;
import com.zhaixin.carrentalbooking.util.DateUtil;
import com.zhaixin.carrentalbooking.util.OrderNoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/24 23:22
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CarRentalOrderService carRentalOrderService;
    @Autowired
    private CarRentalOrderDao carRentalOrderDao;
    @Autowired
    private CarInfoDao carInfoDao;
    @Autowired
    private OrderNoGenerator orderNoGenerator;
    @Autowired
    private CarInfoService carInfoService;

    @Override
    public CommonResult<UserDTO> userLogin(String phone, String password) {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(),
                    ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        User user = userDao.getUserByPhone(phone);
        if(user == null) {
            return new CommonResult<>(ResultCode.USER_NOT_EXIST.getCode(),
                    ResultCode.USER_NOT_EXIST.getMessage(),null);
        }
        if(!user.getPassword().equals(new BCryptPasswordEncoder().encode(password))) {
            return new CommonResult<>(ResultCode.PASSWORD_ERROR.getCode(),
                    ResultCode.PASSWORD_ERROR.getMessage(),null);
        }
        UserDTO userDTO  = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        userDTO.setUsername(user.getUsername());

        return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),userDTO);
    }

    @Override
    public CommonResult userLoginOut(String phone) {
        return null;
    }

    @Override
    public CommonResult userRegister(UserDTO userDTO) {
        if(userDTO == null) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        if(userDao.getUserByPhone(userDTO.getPhone()) != null) {
            return new CommonResult(ResultCode.PHONE_EXIST.getCode(),ResultCode.PHONE_EXIST.getMessage(),null);
        }
        if(userDao.getUserByEmail(userDTO.getEmail()) != null) {
            return new CommonResult(ResultCode.EMAIL_EXIST.getCode(),ResultCode.EMAIL_EXIST.getMessage(),null);
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        // 加密
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        // 默认是普通用户
        user.setRole(0);
        userDao.insert(user);

        return new CommonResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), true);
    }

    // 一个用户一次只能下一单，一辆车，多辆就多次
    @Override
    public CommonResult<String> userRentCarInProgress(Long userId, Long carId, Integer days) {
        if(userId == null || carId == null || days == null) {
            return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        CarRentalOrderDTO carRentalOrderDTO = new CarRentalOrderDTO();
        // 分布式锁生成订单
        String orderNo = orderNoGenerator.getOrderNo();
        Date date = new Date();
        carRentalOrderDTO.setCarId(carId);
        carRentalOrderDTO.setCount(days);
        carRentalOrderDTO.setOrderNo(orderNo);
        carRentalOrderDTO.setState(0);
        carRentalOrderDTO.setUserId(userId);
        carRentalOrderDTO.setStartTime(date);
        carRentalOrderDTO.setExpireTime(DateUtil.getCurrentTimeAfterN(date,10));
        carRentalOrderDTO.setEndTime(DateUtil.pushNumAfterDays(date, days));
        CommonResult commonResult = carRentalOrderService.createOrder(carRentalOrderDTO);
        if(commonResult.getCode() != 200) {
            return new CommonResult<>(ResultCode.ERROR.getCode(),ResultCode.ERROR.getMessage(),null);
        }
        // 预扣库存
        carInfoService.carStockSub(carId);
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), orderNo);
    }

    @Override
    public CommonResult userRentCarCancel(String orderNo) {
        if(StringUtils.isEmpty(orderNo)) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        CarRentalOrder carRentalOrder = carRentalOrderDao.getOrderInfoByOrderNo(orderNo);
        if(carRentalOrder == null) {
            return new CommonResult(ResultCode.ORDER_NOT_EXIST.getCode(),ResultCode.ORDER_NOT_EXIST.getMessage(),orderNo);
        }
        carRentalOrderDao.updateOrderStateByOrderNo(orderNo, 2);
        // 恢复库存
        carInfoService.carStockAdd(carRentalOrder.getCarId());
        return new CommonResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), orderNo);
    }

    @Override
    public CommonResult userRentCarConfirm(String orderNo) {
        if(StringUtils.isEmpty(orderNo)) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage(),orderNo);
        }
        CarRentalOrder carRentalOrder = carRentalOrderDao.getOrderInfoByOrderNo(orderNo);
        if(carRentalOrder == null) {
            return new CommonResult(ResultCode.ORDER_NOT_EXIST.getCode(),ResultCode.ORDER_NOT_EXIST.getMessage(),orderNo);
        }
        if(new Date().after(carRentalOrder.getExpireTime())) {
            carRentalOrderDao.updateOrderStateByOrderNo(orderNo, 2);
            return new CommonResult(ResultCode.ORDER_EXPIRED_CANCEL.getCode(),ResultCode.ORDER_EXPIRED_CANCEL.getMessage(),orderNo);
        }
        carRentalOrderDao.updateOrderStateByOrderNo(orderNo, 1);
        return new CommonResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), orderNo);
    }

    // 定时任务取消时也要恢复库存
    @Override
    public CommonResult userBackCar(String orderNo) {
        if(StringUtils.isEmpty(orderNo)) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(),ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        // TODO: 超过租车时间的逻辑，如何算价格等，下面先忽略这个情况，默认直接还
        CarRentalOrder carRentalOrder = carRentalOrderDao.getOrderInfoByOrderNo(orderNo);
        if(carRentalOrder == null) {
            return new CommonResult(ResultCode.ORDER_NOT_EXIST.getCode(),ResultCode.ORDER_NOT_EXIST.getMessage(),orderNo);
        }
        carRentalOrderDao.updateOrderStateByOrderNo(orderNo, 3);
        // 恢复库存
        carInfoService.carStockAdd(carRentalOrder.getCarId());

        return new CommonResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),orderNo);
    }
}