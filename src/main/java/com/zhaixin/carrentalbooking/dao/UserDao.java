package com.zhaixin.carrentalbooking.dao;

import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import com.zhaixin.carrentalbooking.entity.*;
import org.springframework.stereotype.Component;

/**
 * 
 * @Author:zhai xin
 * gen by beetlsql mapper 2021-12-24
 */
@Component
public interface UserDao extends BaseMapper<User> {
    @SqlStatement(params = "phone")
	User getUserByPhone(String phone);

    @SqlStatement(params = "email")
    User getUserByEmail(String email);
}
