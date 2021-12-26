package com.zhaixin.carrentalbooking.cotroller;

import com.zhaixin.carrentalbooking.config.MyUserDetailsService;
import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.dao.UserDao;
import com.zhaixin.carrentalbooking.dto.CarRentalOrderPageDTO;
import com.zhaixin.carrentalbooking.entity.User;
import com.zhaixin.carrentalbooking.service.CarRentalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/25 17:05
 */
@Controller
public class CarRentalOrderController {
    @Autowired
    private CarRentalOrderService carRentalOrderService;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/order")
    public String doRentCancel(Model model, Authentication authentication) {
        User user = userDao.getUserByPhone(authentication.getName());
        CommonResult<CarRentalOrderPageDTO> result = carRentalOrderService.getCarRentalOrderListByUserIdOrCarId(user.getId(),null,1,10);
        model.addAttribute("orders",result.getData().getCarRentalOrders());
        return "/order";
    }
}