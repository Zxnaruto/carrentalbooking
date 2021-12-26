package com.zhaixin.carrentalbooking.dto;

import lombok.Data;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/26 14:06
 */
@Data
public class RentDTO {
    private Long carId;
    private Integer days;
    private String phone;
}