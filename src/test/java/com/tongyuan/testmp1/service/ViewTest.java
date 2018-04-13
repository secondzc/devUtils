package com.tongyuan.testmp1.service;

import com.tongyuan.testmp1.dao.ViewMapper;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewTest {
    @Autowired
    private ViewMapper viewMapper;

    @Test
    public void testView() throws Exception{
        List<StudentView> list = viewMapper.selectStudentByTeacherJobNumber("teacher");
        System.out.println(list.size());
        System.out.println(list);
    }
}
