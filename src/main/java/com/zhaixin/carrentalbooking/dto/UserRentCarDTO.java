package com.zhaixin.carrentalbooking.dto;

import lombok.Data;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/25 01:20
 */
@Data
public class UserRentCarDTO {
    private Long carId;
    private Long userId;
    private Integer count;
}