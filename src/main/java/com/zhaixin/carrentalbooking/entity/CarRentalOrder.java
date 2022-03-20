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
@Table(name="car_rental_order")
@Data
public class CarRentalOrder implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 租车天数
     */
    private Integer count;
    /**
     * 订单状态：0:下单中，1:已下单，2:已取消，3:已结束，4:超时
     */
    private Integer state;
    /**
     * 汽车id
     */
    private Long carId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 订单结束时间
     */
    private Date endTime;
    /**
     * 订单过期时间
     */
    private Date expireTime;
    /**
     * 创建订单时间
     */
    private Date startTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
