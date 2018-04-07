package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.UserMapper;
import com.tongyuan.testmp1.entity.User;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.helper.PageHandler;
import com.tongyuan.testmp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageDataResult<User> selectPageByAge(Integer page,Integer limit) {
        return new PageHandler<User>() {

            @Override
            protected List<User> doQuery() {
                return userMapper.selectList(new EntityWrapper<User>().between("age",20,30));
            }
        }.getResult(page,limit);
    }

    @Transactional
    @Override
    public void testTrans() {
        User user = new User();
        user.setId(1l);
        user.setUsername("华中科技");
        userMapper.updateById(user);
        System.out.println(userMapper.selectById(1l));
        Integer a = 1/0;
        userMapper.deleteById(1l);
    }

}
