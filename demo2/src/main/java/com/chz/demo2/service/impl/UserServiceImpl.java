package com.chz.demo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chz.demo2.custom.MyUserDetails;
import com.chz.demo2.entity.User;
import com.chz.demo2.mapper.UserMapper;
import com.chz.demo2.service.UserService;
import com.chz.demo2.utils.JwtHelper;
import com.chz.demo2.utils.ResponseResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public ResponseResult login(User user) {
        /** 1.AuthenticationManager的authenticate进行用户认证
                authenticate需要传入authenticate类型参数。authenticate是接口，需要它的实现类UsernamePasswordAuthenticationToken
                    传入UsernamePasswordAuthenticationToken的参数只有用户名和密码
            2.认证通过，使用userId生成JWT
            3.用户信息存入redis
        **/

        //1
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //2
        if (null == authenticate){
            throw new RuntimeException("认证失败");
        }
        MyUserDetails myUserDetails = (MyUserDetails) authenticate.getPrincipal();
        Long id = myUserDetails.getUser().getId();
        String userName = myUserDetails.getUser().getUserName();

        String token = JwtHelper.createToken(id,userName);
        //3
//        redisTemplate.opsForValue().set(userName,token);

        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        return new ResponseResult(200,"认证成功",map);
    }
}
