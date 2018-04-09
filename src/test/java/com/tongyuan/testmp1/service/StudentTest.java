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
public class StudentTest {
    @Autowired
    private StuinfoService traineeService;
    @Autowired
    private StusummaryService stusummaryService;
    @Autowired
    private StuinfoService stuinfoService;

    @Test
    public void select(){
        System.out.println(traineeService.selectById(1));
    }

    @Test
    public void selectSummary(){
        System.out.println(stusummaryService.selectById(1));
    }

    @Test
    public void selectId(){
        System.out.println(stuinfoService.selectIdByJobNumber("student"));
    }
}
