package com.zhaixin.carrentalbooking.cotroller;

import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.dto.CarInfoPageDTO;
import com.zhaixin.carrentalbooking.service.CarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/25 17:04
 */
@Controller
public class CarInfoController {
    @Autowired
    private CarInfoService carInfoService;

    @RequestMapping("/home")
    public String home(Model model) {
        CommonResult<CarInfoPageDTO> result = carInfoService.showCarInfo(1,10,null);
        if(result!=null && result.getCode() == 200) {
            model.addAttribute("carInfos",result.getData().getCarInfos());
        }
        return "/home";
    }
}