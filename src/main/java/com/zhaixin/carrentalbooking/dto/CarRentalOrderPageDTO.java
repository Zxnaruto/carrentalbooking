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

    public List<CarRentalOrder> getCarRentalOrders() {
        return carRentalOrders;
    }

    public void setCarRentalOrders(List<CarRentalOrder> carRentalOrders) {
        this.carRentalOrders = carRentalOrders;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}