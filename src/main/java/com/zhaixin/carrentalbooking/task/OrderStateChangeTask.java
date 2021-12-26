package com.zhaixin.carrentalbooking.task;

import com.zhaixin.carrentalbooking.dao.CarInfoDao;
import com.zhaixin.carrentalbooking.dao.CarRentalOrderDao;
import com.zhaixin.carrentalbooking.entity.CarRentalOrder;
import com.zhaixin.carrentalbooking.service.CarInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: carrentalbooking
 * @description: 取消超时订单
 * @author: Zhaixin
 * @created: 2021/12/25 15:19
 */
@Component
public class OrderStateChangeTask {

    @Autowired
    private CarRentalOrderDao carRentalOrderDao;
    @Autowired
    private CarInfoDao carInfoDao;
    @Autowired
    private CarInfoService carInfoService;

    // 每五秒一次,将超时的订单取消,并返回库存
    @Scheduled(cron = "*/5 * * * * ?")
    public void orderStateChange() {
        List<CarRentalOrder> list = carRentalOrderDao.getOrderStateCancelByOutTime();
        if(CollectionUtils.isNotEmpty(list)) {
            List<String> orderNos = new ArrayList<>();
            List<Long> carIds = new ArrayList<>();
            list.forEach(elem ->{
                orderNos.add(elem.getOrderNo());
                carIds.add(elem.getCarId());
            });
            carRentalOrderDao.updateOrderStateCancelByOutTime(orderNos);
            carIds.forEach(elem -> {
                carInfoService.carStockAdd(elem);
            });
        }
    }
}