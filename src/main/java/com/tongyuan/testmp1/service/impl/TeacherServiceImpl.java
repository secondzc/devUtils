package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.TeacherMapper;
import com.tongyuan.testmp1.entity.Teacher;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.helper.PageHandler;
import com.tongyuan.testmp1.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper,Teacher> implements TeacherService{

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> selectByDept(String firstDept, String secondDept) {
        return teacherMapper.selectList(
                new EntityWrapper<Teacher>().eq("first_dept",firstDept).eq("second_dept",secondDept));
    }

    @Override
    public PageDataResult<Teacher> selectPage(Integer page, Integer limit) {
        return new PageHandler<Teacher>() {
            @Override
            protected List<Teacher> doQuery() {
                return teacherMapper.selectList(null);
            }
        }.getResult(page,limit);
    }
}
