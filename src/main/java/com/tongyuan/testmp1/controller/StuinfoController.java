package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.entity.Teacher;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.service.StuinfoService;
import com.tongyuan.testmp1.service.ViewService;
import com.tongyuan.testmp1.viewModel.StuTeacherView;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangcy on 2018/4/9
 */
@RequestMapping("/stuinfo")
@Controller
public class StuinfoController extends BaseController{
    @Autowired
    private StuinfoService stuinfoService;
    @Autowired
    private ViewService viewService;

    @PostMapping("/update")
    @ResponseBody
    public JSONObject update(Stuinfo stuinfo){
        Integer id = stuinfoService.selectIdByJobNumber(stuinfo.getJob_number());
        stuinfo.setId(id);
        stuinfoService.updateById(stuinfo);
        return setUpdateResponse();
    }

    @GetMapping("/select")
    @ResponseBody
    public JSONObject select(HttpServletRequest request){
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        String job_number = stuinfo.getJob_number();
        Integer id = stuinfoService.selectIdByJobNumber(job_number);
        return setQueryResponse(stuinfoService.selectById(id));
    }

    @PostMapping("/selectByHr")
    @ResponseBody
    public JSONObject selectByHr(HttpServletRequest request,
                                      @RequestParam("page")Integer page,
                                      @RequestParam("limit")Integer limit){
        Hr hr = (Hr)request.getSession().getAttribute("user");
        PageDataResult<StuTeacherView> result = viewService.selectStuTeaPageByDept(hr.getFirst_dept(),hr.getSecond_dept(),page,limit);
        return setQueryResponse(result);
    }

    @GetMapping("/selectByTeacher")
    @ResponseBody
    public JSONObject selectByTeacher(HttpServletRequest request,
                                      @RequestParam("page")Integer page,
                                      @RequestParam("limit")Integer limit){
        Teacher teacher = (Teacher)request.getSession().getAttribute("user");
        PageDataResult<StudentView> result = viewService.selectStudentPageByTeacherJobNumber(teacher.getJob_number(),page,limit);
        return setQueryResponse(result);
    }
}
