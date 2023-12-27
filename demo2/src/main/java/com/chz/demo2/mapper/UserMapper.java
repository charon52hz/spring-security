package com.chz.demo2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chz.demo2.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
