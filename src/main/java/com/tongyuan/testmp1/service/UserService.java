package com.tongyuan.testmp1.service;

import com.baomidou.mybatisplus.service.IService;
import com.tongyuan.testmp1.entity.User;

import java.util.List;

/**
 * Created by zhangcy on 2018/2/15
 */
public interface UserService extends IService<User>{
    List<User> selectPageByAge();
}
