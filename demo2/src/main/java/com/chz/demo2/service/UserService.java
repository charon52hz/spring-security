package com.chz.demo2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chz.demo2.entity.User;
import com.chz.demo2.utils.ResponseResult;
import org.springframework.stereotype.Service;


public interface UserService extends IService<User> {

    ResponseResult login(User user);
}
