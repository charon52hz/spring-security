package com.chz.demo2.controller;

import com.chz.demo2.entity.User;
import com.chz.demo2.service.UserService;
import com.chz.demo2.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("login")
    @ResponseBody
    public ResponseResult login(@RequestBody User user){
        return userService.login(user);
    }
}
