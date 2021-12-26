package com.zhaixin.carrentalbooking.dao;

import com.zhaixin.carrentalbooking.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/27 01:11
 */
@SpringBootTest
public class UserDaoTest {
    private UserDao userDao;

    @Before
    public void setUp() {
        userDao = Mockito.mock(UserDao.class);
        User user = new User();
        user.setId(1L);
        user.setRole(0);
        user.setPhone("123456");
        user.setPassword("123456");
        user.setEmail("123456@qq.com");
        Mockito.when(userDao.getUserByPhone("123456")).thenReturn(user);
        Mockito.when(userDao.getUserByEmail("123456@qq.com")).thenReturn(user);
    }

    @Test
    public void shouldReturnUserWhenGivePhone() {
        User user = userDao.getUserByPhone("123456");
        User user1 = userDao.getUserByPhone("");
        Assert.assertNotNull(user);
        Assert.assertNull(user1);
    }

    @Test
    public void shouldReturnUserWhenGiveEmail() {
        User user = userDao.getUserByEmail("123456@qq.com");
        User user1 = userDao.getUserByEmail("");
        Assert.assertNotNull(user);
        Assert.assertNull(user1);
    }

}