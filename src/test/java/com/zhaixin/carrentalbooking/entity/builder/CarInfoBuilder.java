package com.zhaixin.carrentalbooking.entity.builder;

import com.zhaixin.carrentalbooking.entity.CarInfo;

import javax.xml.crypto.Data;
import java.util.Date;

public class CarInfoBuilder {
    private Long id = 1L;
    private Integer carStock = 200;
    private Integer carType = 1;
    private Integer state = 1;
    private String carName = "Car";
    private Long rent = 1000L;
    private Date createTime = new Date();
    private Date updateTime = new Date();

    public CarInfoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CarInfoBuilder withCarStock(Integer carStock) {
        this.carStock = carStock;
        return this;
    }

    public CarInfoBuilder withCarType(Integer carType) {
        this.carType = carType;
        return this;
    }

    public CarInfoBuilder withState(Integer state) {
        this.state = state;
        return this;
    }

    public CarInfoBuilder withCarName(String carName) {
        this.carName = carName;
        return this;
    }

    public CarInfoBuilder withRent(Long rent) {
        this.rent = rent;
        return this;
    }

    public CarInfoBuilder withCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public CarInfoBuilder withUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public CarInfo build() {
        return new CarInfo(id, carStock, carType, state, carName, rent, createTime, updateTime);
    }
}
