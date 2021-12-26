package com.zhaixin.carrentalbooking.service.impl;

import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.constant.ResultCode;
import com.zhaixin.carrentalbooking.dao.CarRentalOrderDao;
import com.zhaixin.carrentalbooking.dto.CarRentalOrderDTO;
import com.zhaixin.carrentalbooking.dto.CarRentalOrderPageDTO;
import com.zhaixin.carrentalbooking.entity.CarInfo;
import com.zhaixin.carrentalbooking.entity.CarRentalOrder;
import com.zhaixin.carrentalbooking.service.CarRentalOrderService;
import com.zhaixin.carrentalbooking.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/24 23:23
 */
@Service
public class CarRentalOrderServiceImpl implements CarRentalOrderService {

    @Autowired
    private CarRentalOrderDao carRentalOrderDao;

    @Override
    public CommonResult createOrder(CarRentalOrderDTO carRentalOrderDTO) {
        if(carRentalOrderDTO == null) {
            return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        CarRentalOrder carRentalOrder = new CarRentalOrder();
        carRentalOrder.setCarId(carRentalOrderDTO.getCarId());
        carRentalOrder.setCount(carRentalOrderDTO.getCount());
        carRentalOrder.setOrderNo(carRentalOrderDTO.getOrderNo());
        carRentalOrder.setUserId(carRentalOrderDTO.getUserId());
        carRentalOrder.setState(carRentalOrderDTO.getState());
        carRentalOrder.setStartTime(carRentalOrderDTO.getStartTime());
        carRentalOrder.setExpireTime(carRentalOrderDTO.getExpireTime());
        carRentalOrder.setEndTime(carRentalOrderDTO.getEndTime());
        carRentalOrderDao.insert(carRentalOrder);
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),null);
    }

    @Override
    public CommonResult<CarRentalOrderPageDTO> getCarRentalOrderListByUserIdOrCarId(Long userId, Long carId, Integer pageNum, Integer pageSize) {
        int num = pageNum == null ? 1 : pageNum;
        int size = pageSize == null ? 10 : pageSize;
        LambdaQuery<CarRentalOrder> query = null;
        if(userId == null && carId == null) {
            query = carRentalOrderDao.createLambdaQuery();
        }else if(userId != null) {
            query = carRentalOrderDao.createLambdaQuery().andEq("user_id", userId);
        }else if(carId != null) {
            query = carRentalOrderDao.createLambdaQuery().andEq("car_id", carId);
        }else {
            query = carRentalOrderDao.createLambdaQuery().andEq("user_id",  userId).andEq("car_id",carId);
        }

        if(query == null) {
            return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), null);
        }

        CarRentalOrderPageDTO carRentalOrderPageDTO = new CarRentalOrderPageDTO();
        List<CarRentalOrder> carRentalOrders = query.page(num, size).getList();
        carRentalOrderPageDTO.setCarRentalOrders(carRentalOrders);
        carRentalOrderPageDTO.setTotal(carRentalOrders.size());

        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), carRentalOrderPageDTO);
    }

    @Override
    public CommonResult<CarRentalOrderPageDTO> getAllCarRentalOrderList(Integer pageNum, Integer pageSize, Integer state, Long userId) {
        if(state == null) {
            return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        int num = pageNum == null ? 1 : pageNum;
        int size = pageSize == null ? 10 : pageSize;
        LambdaQuery<CarRentalOrder> query = null;
        if(userId == null) {
            query = carRentalOrderDao.createLambdaQuery().andEq("state", state);
        }else {
            query = carRentalOrderDao.createLambdaQuery().andEq("state", state).andEq("user_id", userId);
        }

        if(query == null) {
            return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), null);
        }

        CarRentalOrderPageDTO carRentalOrderPageDTO = new CarRentalOrderPageDTO();
        List<CarRentalOrder> carRentalOrders = query.page(num, size).getList();
        carRentalOrderPageDTO.setCarRentalOrders(carRentalOrders);
        carRentalOrderPageDTO.setTotal(carRentalOrders.size());

        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), carRentalOrderPageDTO);
    }

    @Override
    public CommonResult updateOrderStateByOrderNo(String orderNo, Integer state) {
        if(StringUtils.isEmpty(orderNo) || state == null) {
            return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        carRentalOrderDao.updateOrderStateByOrderNo(orderNo, state);
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),null);
    }

    @Override
    public CommonResult updateOrderEndTimeByOrderNo(String orderNo, Date date) {
        if(StringUtils.isEmpty(orderNo) || date == null) {
            return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        carRentalOrderDao.updateOrderEndTimeByOrderNo(orderNo, date);
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),null);
    }
}