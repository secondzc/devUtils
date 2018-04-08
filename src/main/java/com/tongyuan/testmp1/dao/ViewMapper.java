package com.tongyuan.testmp1.dao;

import com.tongyuan.testmp1.viewModel.StuTeacherView;
import com.tongyuan.testmp1.viewModel.StudentView;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/8
 */
public interface ViewMapper {
    List<StudentView> selectStudentByTeacherJobNumber(String teacherJobNumber);
    List<StuTeacherView> selectStuTeaByDept(String firstDept, String secondDept);
}
