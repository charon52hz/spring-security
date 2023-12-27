package com.chz.demo2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chz.demo2.custom.MyUserDetails;
import com.chz.demo2.entity.User;
import com.chz.demo2.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.去数据库查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userService.getOne(wrapper);
        if (null == user){
            throw new RuntimeException("登录失败");
        }
        //TODO 2.查询用户对应的权限

        //3.封装成UserDetails返回
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUser(user);

        return userDetails;
    }
}
