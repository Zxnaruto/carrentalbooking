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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}