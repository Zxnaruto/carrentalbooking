package com.zhaixin.carrentalbooking.config;

import com.zhaixin.carrentalbooking.dao.UserDao;
import com.zhaixin.carrentalbooking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: carrentalbooking
 * @description:
 * @author: Zhaixin
 * @created: 2021/12/24 13:42
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByPhone(username);
        if(user == null) {
            throw new UsernameNotFoundException("The user " + username + "not exist, please register");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.
                springframework.
                security.
                core.
                userdetails.
                User(username, user.getPassword(), grantedAuthorities);
    }
}