package com.zhaixin.carrentalbooking.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class CarInfoTest {
    @Test
    public void carInfoTest() {
        CarInfo carInfo = new CarInfo();
        Date date = new Date();
        carInfo.setCreateTime(date);
        carInfo.setUpdateTime(date);
        carInfo.setState(1);
        carInfo.setRent(200L);
        carInfo.setCarType(2);
        carInfo.setId(1L);
        carInfo.setCarStock(300);
        carInfo.setCarName("Test");

        Assert.assertEquals(carInfo.getCarName(), "Test");
        Assert.assertEquals(carInfo.getCarStock().intValue(), 300);
        Assert.assertEquals(carInfo.getId().intValue(), 1);
        Assert.assertEquals(carInfo.getCarType().intValue(), 2);
        Assert.assertEquals(carInfo.getRent(), new Long(200));
        Assert.assertEquals(carInfo.getState().intValue(), 1);
        Assert.assertEquals(carInfo.getCreateTime(), date);
        Assert.assertEquals(carInfo.getUpdateTime(), date);
    }

}
