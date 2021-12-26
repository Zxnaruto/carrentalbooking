package com.zhaixin.carrentalbooking.dao;

import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import com.zhaixin.carrentalbooking.entity.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 
 * @Author:zhai xin
 * gen by beetlsql mapper 2021-12-24
 */
@Component
public interface CarRentalOrderDao extends BaseMapper<CarRentalOrder> {
    @SqlStatement(params = "orderNo,state")
	int updateOrderStateByOrderNo(String orderNo, Integer state);

    @SqlStatement(params = "orderNo,date")
    int updateOrderEndTimeByOrderNo(String orderNo, Date date);

    @SqlStatement(params = "orderNo")
    CarRentalOrder getOrderInfoByOrderNo(String orderNo);

    @SqlStatement(params = "orderNos")
    int updateOrderStateCancelByOutTime(List<String> orderNos);

    List<CarRentalOrder> getOrderStateCancelByOutTime();
}
