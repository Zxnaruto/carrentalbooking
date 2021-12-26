package com.zhaixin.carrentalbooking.dto;

import com.zhaixin.carrentalbooking.entity.CarRentalOrder;
import lombok.Data;

import java.util.List;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/25 02:21
 */
@Data
public class CarRentalOrderPageDTO {
    List<CarRentalOrder> carRentalOrders;
    Integer total;
}