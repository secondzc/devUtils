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
public class TraineeServiceTest {
    @Autowired
    private TraineeService traineeService;

    @Test
    public void select(){
        System.out.println(traineeService.selectById(1));
    }
}
