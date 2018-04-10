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

    /*
    admin或学生更新学生基本信息
     */
    @PostMapping("/update")
    @ResponseBody
    public JSONObject update(Stuinfo stuinfo){
        stuinfoService.updateById(stuinfo);
        return setUpdateResponse();
    }

    /*
    学生查看自己的基本信息
     */
    @GetMapping("/select")
    @ResponseBody
    public JSONObject select(HttpServletRequest request){
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        return setQueryResponse(stuinfoService.selectById(stuinfo.getId()));
    }

    /*
    hr分页查看所有部门所有学生+搜索
     */
    @PostMapping("/selectByHr")
    @ResponseBody
    public JSONObject selectByHr(HttpServletRequest request,
                                      @RequestParam("page")Integer page,
                                      @RequestParam("limit")Integer limit,
                                      @RequestParam("key")String key){
        Hr hr = (Hr)request.getSession().getAttribute("user");
        PageDataResult<StuTeacherView> result = viewService.selectStuTeaPageByDept(hr.getFirst_dept(),hr.getSecond_dept(),page,limit,key);
        return setQueryResponse(result);
    }

    /*
    导师分页查看所有学生
     */
    @GetMapping("/selectByTeacher")
    @ResponseBody
    public JSONObject selectByTeacher(HttpServletRequest request,
                                      @RequestParam("page")Integer page,
                                      @RequestParam("limit")Integer limit){
        Teacher teacher = (Teacher)request.getSession().getAttribute("user");
        PageDataResult<StudentView> result = viewService.selectStudentPageByTeacherJobNumber(teacher.getJob_number(),page,limit);
        return setQueryResponse(result);
    }

    /*
    admin分页查看所有学生+搜索
     */
    @GetMapping("/selectByAdmin")
    @ResponseBody
    public JSONObject selectByAdmin(@RequestParam("page")Integer page,
                                    @RequestParam("limit")Integer limit,
                                    @RequestParam("key")String key){
        PageDataResult result = stuinfoService.selectVagueStuPage(page,limit,key);
        return setQueryResponse(result);
    }

    /*
    admin删除学生
     */
    @PostMapping("/delete")
    @ResponseBody
    public JSONObject deleteByAdmin(Stuinfo stuinfo){
        return setDeleteResponse(stuinfoService.deleteById(stuinfo.getId()));
    }

    /*
    admin新增学生
     */
    @PostMapping("/add")
    @ResponseBody
    public JSONObject addByAdmin(Stuinfo stuinfo){
        String psw = stuinfo.getJob_number().substring(stuinfo.getJob_number().length()-6);
        stuinfo.setEncrypt_password(psw);
        stuinfoService.insert(stuinfo);
        return  setInsertResponse();
    }
}
