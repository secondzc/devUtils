package com.tongyuan.testmp1.service.impl;

import com.tongyuan.testmp1.dao.ViewMapper;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.helper.PageHandler;
import com.tongyuan.testmp1.service.ViewService;
import com.tongyuan.testmp1.viewModel.StuTeacherView;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/8
 */
@Service
public class ViewServiceImpl implements ViewService{

    @Autowired
    private ViewMapper viewMapper;

    @Override
    public PageDataResult<StudentView> selectStudentPageByTeacherJobNumber(final String teacherJobNumber, Integer page, Integer limit) {
        return new PageHandler<StudentView>() {
            @Override
            protected List<StudentView> doQuery() {
                return viewMapper.selectStudentByTeacherJobNumber(teacherJobNumber);
            }
        }.getResult(page,limit);
    }

    @Override
    public PageDataResult<StuTeacherView> selectStuTeaPageByDept(final String firstDept, final String secondDept, Integer page, Integer limit,final String key) {
        return new PageHandler<StuTeacherView>() {
            @Override
            protected List<StuTeacherView> doQuery() {
                return viewMapper.selectStuTeaByDept(firstDept,secondDept,key);
            }
        }.getResult(page,limit);
    }
}
