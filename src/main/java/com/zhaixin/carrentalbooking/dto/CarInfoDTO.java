package com.zhaixin.carrentalbooking.dto;

import lombok.Data;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/24 22:57
 */
@Data
public class CarInfoDTO {
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
}