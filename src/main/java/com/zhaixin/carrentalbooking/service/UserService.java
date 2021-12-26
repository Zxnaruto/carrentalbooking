package com.zhaixin.carrentalbooking.service;

import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.dto.UserDTO;
import com.zhaixin.carrentalbooking.dto.UserRentCarDTO;

public interface UserService {
    /*
    * 用户登录
    * */
    CommonResult<UserDTO> userLogin(String phone, String password);

    /*
    * 用户登出
    * */
    CommonResult userLoginOut(String phone);

    /*
    * 用户注册
    * */
    CommonResult userRegister(UserDTO userDTO);

    /*
    * 用户租车进行中,创建初始订单，预扣库存
    * */
    CommonResult<String> userRentCarInProgress(Long userId, Long carId, Integer days);

    /*
     * 用户租车取消租车，修改订单状态，恢复库存
     * */
    CommonResult userRentCarCancel(String orderNo);

    /*
     * 用户租车确认租车，修改订单状态，真实减库存
     * */
    CommonResult userRentCarConfirm(String orderNo);

    /*
    * 用户还车,根据订单id还，所以需要查出用户所有已下单的记录
    * */
    CommonResult userBackCar(String orderNo);
}
