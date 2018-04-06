package com.tongyuan.testmp1.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangcy on 2018/4/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HrServiceTest {
    @Autowired
    private HrService hrService;

    @Test
    public void selecthr(){
        System.out.println(hrService.selectById(1));
    }
}
