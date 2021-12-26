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
}