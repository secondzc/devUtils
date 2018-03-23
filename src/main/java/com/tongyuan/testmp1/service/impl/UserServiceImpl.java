package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.UserMapper;
import com.tongyuan.testmp1.entity.User;
import com.tongyuan.testmp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by zhangcy on 2018/2/15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    /*
    分页查询年龄在20-30的user
     */
    @Override
    public List<User> selectPageByAge() {
        return userMapper.selectPage(new Page<User>(1,3),new EntityWrapper<User>().between("age",20,30));
    }
}
