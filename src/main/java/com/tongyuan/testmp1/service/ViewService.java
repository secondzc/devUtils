package com.tongyuan.testmp1.service;

import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.viewModel.StuTeacherView;
import com.tongyuan.testmp1.viewModel.StudentView;

/**
 * Created by zhangcy on 2018/4/8
 */
public interface ViewService {
    PageDataResult<StudentView> selectStudentPageByTeacherJobNumber(String teacherJobNumber, Integer page, Integer limit);
    PageDataResult<StuTeacherView> selectStuTeaPageByDept(String firstDept, String secondDept, Integer page, Integer limit,String key);
}
