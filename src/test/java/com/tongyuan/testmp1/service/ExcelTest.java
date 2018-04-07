package com.tongyuan.testmp1.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by zhangcy on 2018/4/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {
    @Autowired
    private ExcelService excelService;

    @Test
    public void test() throws Exception{
        excelService.parse(new FileInputStream(new File("/Users/zhangcy/Documents/test/信息表.xlsx")));
    }
}
