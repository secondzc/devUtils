package com.tongyuan.testmp1.service;

import com.tongyuan.testmp1.dao.OrderDetailMapper;
import com.tongyuan.testmp1.entity.Userr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangcy on 2018/2/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserrServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Test
    public void queryUser() throws Exception {
        Userr userr = userService.selectById(1l);
        System.out.println(userr.getAge());
    }


    @Test
    public void testUpdate() throws Exception{
        orderDetailMapper.decreaseGoodsId();
    }

    @Test
    public void trans() throws Exception{
        userService.testTrans();
    }
}