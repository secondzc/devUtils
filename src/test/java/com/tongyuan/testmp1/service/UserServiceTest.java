package com.tongyuan.testmp1.service;

import com.tongyuan.testmp1.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zhangcy on 2018/2/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void queryUser() throws Exception {
        User user = userService.selectById(1l);
        System.out.println(user.getAge());
    }

    @Test
    public void selectPageByAgeTest() throws Exception{
        List<User> users = userService.selectPageByAge();
        System.out.println(users);
    }

}