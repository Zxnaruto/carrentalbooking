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

}
