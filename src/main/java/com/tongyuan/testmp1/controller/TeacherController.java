package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.entity.Teacher;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.service.ViewService;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangcy on 2018/4/8
 */
@RequestMapping("/teacher")
@Controller
public class TeacherController extends BaseController{

    @Autowired
    private ViewService viewService;

    @GetMapping("/students")
    @ResponseBody
    public JSONObject showAllStudents(HttpServletRequest request,
                                      @RequestParam("page")Integer page,
                                      @RequestParam("limit")Integer limit){
        Teacher teacher = (Teacher)request.getSession().getAttribute("user");
        PageDataResult<StudentView> result = viewService.selectStudentPageByTeacherJobNumber(teacher.getJob_number(),page,limit);
        return setQueryResponse(result);
    }
}
