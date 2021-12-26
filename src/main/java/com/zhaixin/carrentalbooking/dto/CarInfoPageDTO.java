package com.zhaixin.carrentalbooking.dto;

import com.zhaixin.carrentalbooking.entity.CarInfo;
import lombok.Data;

import java.util.List;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/25 00:04
 */
@Data
public class CarInfoPageDTO {
    List<CarInfo> carInfos;
    Integer total;
}