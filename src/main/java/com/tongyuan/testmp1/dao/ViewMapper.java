package com.tongyuan.testmp1.dao;

import com.tongyuan.testmp1.viewModel.EvaluationView;
import com.tongyuan.testmp1.viewModel.StuTeacherView;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/8
 */
public interface ViewMapper {
    List<StudentView> selectStudentByTeacherJobNumber(String teacherJobNumber);
    List<StuTeacherView> selectStuTeaByDept(@Param("firstDept") String firstDept, @Param("secondDept")String secondDept, @Param("key") String key);
    EvaluationView selectEvaluationById(Integer id);
    List<EvaluationView> selectAllEvaluations();
    StudentView selectStuViewById(Integer id);
}
