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

    public CarInfoPageDTO() {}
    public CarInfoPageDTO(List<CarInfo> carInfos, Integer total) {
        this.carInfos = carInfos;
        this.total = total;
    }

    public List<CarInfo> getCarInfos() {
        return carInfos;
    }

    public void setCarInfos(List<CarInfo> carInfos) {
        this.carInfos = carInfos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}