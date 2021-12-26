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
@Table(name="user")
@Data
public class User implements Serializable {

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
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
