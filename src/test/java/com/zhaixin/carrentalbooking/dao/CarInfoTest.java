package com.zhaixin.carrentalbooking.dao;

import com.zhaixin.carrentalbooking.entity.CarInfo;
import com.zhaixin.carrentalbooking.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.regex.Matcher;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/27 01:27
 */
@SpringBootTest
public class CarInfoTest {
    private CarInfoDao carInfoDao;

    @Before
    public void setUp() {
        carInfoDao = Mockito.mock(CarInfoDao.class);
        CarInfo carInfo = new CarInfo();
        carInfo.setState(1);
        carInfo.setRent(200L);
        carInfo.setCarType(1);
        carInfo.setCarStock(2);
        carInfo.setId(1L);
        carInfo.setCarName("BWM");
        Mockito.when(carInfoDao.getCarInfoByCarName("BWM")).thenReturn(carInfo);
        Mockito.when(carInfoDao.upAndDownCarInfo(1L,0)).thenReturn(1);
    }

    @Test
    public void shouldReturnCarInfoWhenGiveCarName() {
        CarInfo carInfo = carInfoDao.getCarInfoByCarName("BWM");
        CarInfo carInfo1 = carInfoDao.getCarInfoByCarName("");
        Assert.assertNull(carInfo1);
        Assert.assertNotNull(carInfo);
    }

    @Test
    public void shouldReturnOneWhenGivenExistCarId() {
        int result = carInfoDao.upAndDownCarInfo(1L,0);
        Assert.assertEquals(1, result);
    }

}