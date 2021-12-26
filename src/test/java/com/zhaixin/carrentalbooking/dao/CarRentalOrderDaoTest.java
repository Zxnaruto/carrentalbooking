package com.zhaixin.carrentalbooking.dao;

import com.zhaixin.carrentalbooking.entity.CarRentalOrder;
import com.zhaixin.carrentalbooking.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/27 02:08
 */
@SpringBootTest
public class CarRentalOrderDaoTest {
    private CarRentalOrderDao carRentalOrderDao;

    @Before
    public void setUp() {
        carRentalOrderDao = Mockito.mock(CarRentalOrderDao.class);
        CarRentalOrder carRentalOrder = new CarRentalOrder();
        carRentalOrder.setId(1L);
        carRentalOrder.setOrderNo("123");
        carRentalOrder.setState(1);
        carRentalOrder.setCount(2);
        carRentalOrder.setCarId(1L);
        Mockito.when(carRentalOrderDao.getOrderInfoByOrderNo("123")).thenReturn(carRentalOrder);
        Mockito.when(carRentalOrderDao.updateOrderStateByOrderNo("123", 1)).thenReturn(1);
    }

    @Test
    public void shouldReturnCarRentalOrderWhenGiveCorrectOrderNo() {
        CarRentalOrder carRentalOrder = carRentalOrderDao.getOrderInfoByOrderNo("123");
        CarRentalOrder carRentalOrder1 = carRentalOrderDao.getOrderInfoByOrderNo("");

        Assert.assertNotNull(carRentalOrder);
        Assert.assertNull(carRentalOrder1);
    }

    @Test
    public void shouldReturnOneWhenGiveExistOrderNoToUpdate() {
        int result = carRentalOrderDao.updateOrderStateByOrderNo("123", 1);
        Assert.assertEquals(1, result);
    }


}