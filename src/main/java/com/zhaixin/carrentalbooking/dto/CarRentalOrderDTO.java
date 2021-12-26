package com.zhaixin.carrentalbooking.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/24 23:00
 */
@Data
public class CarRentalOrderDTO {
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

}