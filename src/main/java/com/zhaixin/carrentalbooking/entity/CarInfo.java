package com.zhaixin.carrentalbooking.entity;

import java.util.Date;
import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * @PackageName:com.zhaixin.carrentalbooking.entity
 * @Author:zhai xin
 * <p>
 * gen by zhai xin 2021-12-24
 */
@Table(name="car_info")
@Data
public class CarInfo implements Serializable {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 汽车库存
     */
    private Integer carStock;
    /**
     * 汽车类型：1:BMW , 2:Toyota
     */
    private Integer carType;
    /**
     * 汽车上下架状态：0:上架 , 1:下架
     */
    private Integer state;
    /**
     * 汽车名称
     */
    private String carName;
    /**
     * 租金/天
     */
    private Long rent;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public CarInfo() {
    }

    public CarInfo(Long id, Integer carStock, Integer carType, Integer state, String carName, Long rent, Date createTime, Date updateTime) {
        this.id = id;
        this.carStock = carStock;
        this.carType = carType;
        this.state = state;
        this.carName = carName;
        this.rent = rent;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCarStock() {
        return carStock;
    }

    public void setCarStock(Integer carStock) {
        this.carStock = carStock;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Long getRent() {
        return rent;
    }

    public void setRent(Long rent) {
        this.rent = rent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
