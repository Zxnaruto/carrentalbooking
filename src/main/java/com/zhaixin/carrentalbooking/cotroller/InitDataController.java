package com.zhaixin.carrentalbooking.cotroller;

import com.zhaixin.carrentalbooking.dao.CarInfoDao;
import com.zhaixin.carrentalbooking.entity.CarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/26 20:42
 */
@RestController
public class InitDataController {
    @Autowired
    private CarInfoDao carInfoDao;

    @RequestMapping("/init")
    public String initData() {
        try {
            CarInfo carInfo = new CarInfo();
            carInfo.setCarName("Toyota Camry");
            carInfo.setCarStock(2);
            carInfo.setCarType(2);
            carInfo.setRent(100L);
            carInfo.setState(0);
            carInfo.setCreateTime(new Date());
            carInfo.setUpdateTime(new Date());
            carInfoDao.insert(carInfo);
            CarInfo carInfo2 = new CarInfo();
            carInfo2.setCarName("BMW 650");
            carInfo2.setCarStock(2);
            carInfo2.setCarType(1);
            carInfo2.setRent(1000L);
            carInfo2.setState(0);
            carInfo2.setCreateTime(new Date());
            carInfo2.setUpdateTime(new Date());
            carInfoDao.insert(carInfo2);
        }catch (Exception e) {

        }
        return "success";
    }
}