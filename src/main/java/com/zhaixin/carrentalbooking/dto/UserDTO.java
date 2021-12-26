package com.zhaixin.carrentalbooking.dto;

import lombok.Data;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/24 22:51
 */
@Data
public class UserDTO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户角色：0:user , 1:admin
     */
    private Integer role;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户名
     */
    private String username;
}