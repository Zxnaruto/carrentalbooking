package com.zhaixin.carrentalbooking.service.impl;

import com.zhaixin.carrentalbooking.config.RedisLock;
import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.constant.RedisConstant;
import com.zhaixin.carrentalbooking.constant.ResultCode;
import com.zhaixin.carrentalbooking.dao.CarInfoDao;
import com.zhaixin.carrentalbooking.dto.CarInfoDTO;
import com.zhaixin.carrentalbooking.dto.CarInfoPageDTO;
import com.zhaixin.carrentalbooking.entity.CarInfo;
import com.zhaixin.carrentalbooking.entity.User;
import com.zhaixin.carrentalbooking.service.CarInfoService;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/24 23:22
 */
@Service
public class CarInfoServiceImpl implements CarInfoService {

    @Autowired
    private CarInfoDao carInfoDao;
    @Autowired
    private RedisLock redisLock;

    @Override
    public CommonResult<CarInfoPageDTO> showCarInfo(Integer pageNum, Integer pageSize, Integer type) {
        int num = pageNum == null ? 1 : pageNum;
        int size = pageSize == null ? 10 : pageSize;
        LambdaQuery<CarInfo> query = null;
        if(type != null) {
            query = carInfoDao.createLambdaQuery().andEq("car_type", type).andGreat("car_stock",0);
        }else {
            query = carInfoDao.createLambdaQuery().andGreat("car_stock",0);;
        }
        if(query == null) {
            return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), null);
        }
        CarInfoPageDTO carInfoPageDTO = new CarInfoPageDTO();
        List<CarInfo> carInfos = query.page(num, size).getList();
        carInfoPageDTO.setCarInfos(carInfos);
        carInfoPageDTO.setTotal(carInfos.size());

        return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), carInfoPageDTO);
    }

    @Override
    public CommonResult addCarInfo(CarInfoDTO carInfoDTO) {
        if(carInfoDTO == null || StringUtils.isEmpty(carInfoDTO.getCarName()) || carInfoDTO.getCarType() == null) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        if(carInfoDao.getCarInfoByCarName(carInfoDTO.getCarName())!=null || carInfoDao.getCarInfoByCarName(carInfoDTO.getCarName()).getCarName().trim().equals(carInfoDTO.getCarName().trim())) {
            return new CommonResult(ResultCode.CAR_EXIST.getCode(), ResultCode.CAR_EXIST.getMessage(),null);
        }
        if(carInfoDTO.getCarType()<=0 || carInfoDTO.getCarType() > 2) {
            return new CommonResult(ResultCode.CAR_TYPE_NOT_EXIST.getCode(), ResultCode.CAR_TYPE_NOT_EXIST.getMessage(),null);
        }
        CarInfo carInfo = new CarInfo();
        carInfo.setCarName(carInfoDTO.getCarName());
        carInfo.setCarStock(carInfoDTO.getCarStock()==null ? 0 : carInfoDTO.getCarStock());
        carInfo.setCarType(carInfoDTO.getCarType());
        carInfo.setRent(carInfoDTO.getRent() == null ? 1000 : carInfoDTO.getRent());
        carInfo.setState(0);
        carInfoDao.insert(carInfo);
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    @Override
    public CommonResult upAndDownCarInfo(Long carId, Integer state) {
        if(carId == null || state == null|| state < 0 || state > 1) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        carInfoDao.upAndDownCarInfo(carId, state);

        return new CommonResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), null);
    }

    @Override
    public CommonResult updateCarInfo(CarInfoDTO carInfoDTO) {
        if(carInfoDTO == null || carInfoDTO.getId() == null) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),null);
        }
        CarInfo carInfo = new CarInfo();
        carInfo.setState(carInfoDTO.getState());
        carInfo.setRent(carInfoDTO.getRent());
        carInfo.setCarType(carInfoDTO.getCarType());
        carInfo.setCarStock(carInfoDTO.getCarStock());
        carInfo.setCarName(carInfoDTO.getCarName());
        carInfo.setId(carInfoDTO.getId());
        carInfoDao.updateTemplateById(carInfo);
        return new CommonResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), null);
    }

    @Override
    public CommonResult carStockAdd(Long carId) {
        if(carId == null) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),carId);
        }
        String token = UUID.randomUUID().toString().replace("-","") + carId;
        try {
            if (!StringUtils.isEmpty(redisLock.lock(RedisConstant.REDIS_CAR_STOCK_ADD_KEY + carId, token, 3))) {
                carInfoDao.addCarStock(carId);
            }
        }finally {
            redisLock.unlock(RedisConstant.REDIS_CAR_STOCK_ADD_KEY + carId, token);
        }
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),carId);
    }

    @Override
    public CommonResult carStockSub(Long carId) {
        if(carId == null) {
            return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getMessage(),carId);
        }
        String token = UUID.randomUUID().toString().replace("-","") + carId;
        try {
            if (!StringUtils.isEmpty(redisLock.lock(RedisConstant.REDIS_CAR_STOCK_ADD_KEY + carId, token, 3))) {
                carInfoDao.subCarStock(carId);
            }
        }finally {
            redisLock.unlock(RedisConstant.REDIS_CAR_STOCK_ADD_KEY + carId, token);
        }
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),carId);
    }
}