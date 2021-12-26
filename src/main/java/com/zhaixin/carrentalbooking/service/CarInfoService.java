package com.zhaixin.carrentalbooking.service;

import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.dto.CarInfoDTO;
import com.zhaixin.carrentalbooking.dto.CarInfoPageDTO;
import com.zhaixin.carrentalbooking.entity.User;

import java.util.List;

public interface CarInfoService {
    /*
    * 展示所有可租车信息或者根据类型展示所有车信息
    * */
    CommonResult<CarInfoPageDTO> showCarInfo(Integer pageNum, Integer pageSize, Integer type);

    /*
    * 新增租车信息
    * */
    CommonResult addCarInfo(CarInfoDTO carInfoDTO);

    /*
    * 上下架租车信息
    * */
    CommonResult upAndDownCarInfo(Long carId, Integer state);

    /*
    * 修改租车信息
    * */
    CommonResult updateCarInfo(CarInfoDTO carInfoDTO);

    /*
    * 库存增加，默认1
    * */
    CommonResult carStockAdd(Long carId);

    /*
    * 库存减少，默认1
    * */
    CommonResult carStockSub(Long carId);

}
