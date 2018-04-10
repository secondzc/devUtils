package com.tongyuan.testmp1.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tongyuan.testmp1.dao.TeacherMapper;
import com.tongyuan.testmp1.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void testSelectByDept(){
        List<Teacher> teachers =  teacherService.selectByDept("总部","技术部");
        System.out.println(teachers);
    }

    @Test
    public void testSelect(){
        Teacher teacher = teacherService.selectById(1);
        System.out.println(teacher);
    }

    @Test
    public void testSelectNull(){
        List<Teacher> teacherList = teacherMapper.selectList(new EntityWrapper<Teacher>().eq("id",4));
        System.out.println(teacherList.size());
    }
}
