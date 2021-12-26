package com.zhaixin.carrentalbooking.service;

import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.dto.CarRentalOrderDTO;
import com.zhaixin.carrentalbooking.dto.CarRentalOrderPageDTO;

import java.util.Date;

public interface CarRentalOrderService {
    /*
    * 创建订单
    * */
    CommonResult createOrder(CarRentalOrderDTO carRentalOrderDTO);

    /*
    * 根据用户id或者carId查询订单,或者id为null,返回全部订单信息
    * */
    CommonResult<CarRentalOrderPageDTO> getCarRentalOrderListByUserIdOrCarId(Long userId, Long carId, Integer pageNum, Integer pageSize);

    /*
    * 根据用户id和订单类型返回订单列表
    * */
    CommonResult<CarRentalOrderPageDTO> getAllCarRentalOrderList(Integer pageNum, Integer pageSize, Integer state, Long userId);

    /*
    * 修改订单状态
    * */
    CommonResult updateOrderStateByOrderNo(String orderNo, Integer state);

    /*
    * 新增或修改还车日期
    * */
    CommonResult updateOrderEndTimeByOrderNo(String orderNo, Date date);
}
