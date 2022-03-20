package com.zhaixin.carrentalbooking.dto;

import lombok.Data;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/26 12:42
 */
@Data
public class CarStockInfoDTO {
    private Long id;
    private String carName;
    private Integer carStock;
    private Long rent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getCarStock() {
        return carStock;
    }

    public void setCarStock(Integer carStock) {
        this.carStock = carStock;
    }

    public Long getRent() {
        return rent;
    }

    public void setRent(Long rent) {
        this.rent = rent;
    }
}