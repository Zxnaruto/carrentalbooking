package com.zhaixin.carrentalbooking.dao;

import com.zhaixin.carrentalbooking.dto.CarStockInfoDTO;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import com.zhaixin.carrentalbooking.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * @Author:zhai xin
 * gen by beetlsql mapper 2021-12-24
 */
@Component
public interface CarInfoDao extends BaseMapper<CarInfo> {
    @SqlStatement(params = "carName")
    CarInfo getCarInfoByCarName(String carName);

    @SqlStatement(params = "carId,state")
    int upAndDownCarInfo(Long carId, Integer state);

    @SqlStatement(params = "carId")
    int addCarStock(Long carId);

    @SqlStatement(params = "carId")
    int subCarStock(Long carId);

}
