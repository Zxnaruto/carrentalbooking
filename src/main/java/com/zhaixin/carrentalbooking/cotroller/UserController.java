package com.zhaixin.carrentalbooking.cotroller;

import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.dao.UserDao;
import com.zhaixin.carrentalbooking.dto.CarInfoPageDTO;
import com.zhaixin.carrentalbooking.dto.CarRentalOrderPageDTO;
import com.zhaixin.carrentalbooking.dto.RentDTO;
import com.zhaixin.carrentalbooking.dto.UserDTO;
import com.zhaixin.carrentalbooking.entity.User;
import com.zhaixin.carrentalbooking.service.CarInfoService;
import com.zhaixin.carrentalbooking.service.CarRentalOrderService;
import com.zhaixin.carrentalbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/25 17:04
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CarRentalOrderService carRentalOrderService;

    @RequestMapping({"/", "/login"})
    public String userLogin() {
        return "/login";
    }

    @RequestMapping("/logout")
    public String userLogout() {
        return "/login";
    }

    @RequestMapping("/fail")
    public String loginError() {
        return "/error";
    }

    @RequestMapping("/register")
    public String register() {
        return "/register";
    }

    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    public String doRegister(UserDTO userDTO) {
        if (userDTO != null) {
            userService.userRegister(userDTO);
        }
        return "/login";
    }

    @RequestMapping("/rent")
    public String rent(Model model) {
        CommonResult<CarInfoPageDTO> result = carInfoService.showCarInfo(1, 10, null);
        if (result != null && result.getCode() == 200) {
            model.addAttribute("carInfos", result.getData().getCarInfos());
        }
        return "/rent";
    }

    @RequestMapping(value = "/dorent", method = RequestMethod.POST)
    public String doRent(RentDTO rentDTO, Model model) {
        if (rentDTO == null || rentDTO.getCarId() == null || rentDTO.getDays() == null || StringUtils.isEmpty(rentDTO.getPhone())) {
            return "/error";
        }
        User user = userDao.getUserByPhone(rentDTO.getPhone());
        if (user == null) {
            return "/error";
        }
        CommonResult<String> result = userService.userRentCarInProgress(user.getId(), rentDTO.getCarId(), rentDTO.getDays());
        model.addAttribute("orderNo", result.getData());
        return "/confirm";
    }

    @RequestMapping(value = "/rentconfirm", method = RequestMethod.POST)
    public String doRentConfirm(@RequestParam String orderNo) {
        userService.userRentCarConfirm(orderNo);
        return "redirect:/home";
    }

    @RequestMapping(value = "/rentcancel", method = RequestMethod.POST)
    public String doRentCancel(@RequestParam String orderNo) {
        userService.userRentCarCancel(orderNo);
        return "redirect:/home";
    }

    @RequestMapping("/back")
    public String back(Model model, Authentication authentication) {
        User user = userDao.getUserByPhone(authentication.getName());
        CommonResult<CarRentalOrderPageDTO> result = carRentalOrderService.getCarRentalOrderListByUserIdOrCarId(user.getId(), null, 1, 10);
        model.addAttribute("orders", result.getData().getCarRentalOrders());
        return "/back";
    }

    @RequestMapping(value = "/doback", method = RequestMethod.POST)
    public String doback(@RequestParam String orderNo) {
        userService.userBackCar(orderNo);
        return "redirect:/home";
    }
}