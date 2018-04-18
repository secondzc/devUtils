package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.entity.Teacher;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.helper.PwdHelper;
import com.tongyuan.testmp1.service.TeacherService;
import com.tongyuan.testmp1.service.ViewService;
import com.tongyuan.testmp1.util.SecurityUtil;
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
    private TeacherService teacherService;

    /*
    admin分页查询所有导师
     */
    @GetMapping("/select")
    @ResponseBody
    public JSONObject select(@RequestParam("page")Integer page,
                             @RequestParam("limit")Integer limit){
        PageDataResult<Teacher> result = teacherService.selectPage(page,limit);
        return setQueryResponse(result);
    }


    /*
    admin新增导师
     */
    @PostMapping("/add")
    @ResponseBody
    public JSONObject insertByAdmin(Teacher teacher){
        String psw = PwdHelper.getPwd(teacher.getJob_number());
        teacher.setEncrypt_password(psw);
        teacherService.insert(teacher);
        return setInsertResponse();
    }

    /*
    admin更改导师
     */
    @PostMapping("/update")
    @ResponseBody
    public JSONObject updateByAdmin(Teacher teacher){
        teacherService.updateById(teacher);
        return setUpdateResponse();
    }

    /*
    admin批量删除导师
     */
    @GetMapping("/batchDelete")
    @ResponseBody
    public JSONObject batchDelete(String id){
        String[] ids = id.split(",");
        for(String one:ids){
            teacherService.deleteById(Integer.valueOf(one));
        }
        return setBatchDeleteResponse();
    }

    /*
    修改之前：admin获取导师信息
     */
    @GetMapping("/selectById")
    @ResponseBody
    public JSONObject selectById(Integer id){
        return setQueryResponse(teacherService.selectById(id));
    }

    /*
    admin重置导师密码
     */
    @GetMapping("/resetPwd")
    @ResponseBody
    public JSONObject resetPwd(Integer id){
        Teacher teacher = teacherService.selectById(id);
        String jobNum = teacher.getJob_number();
        String pwd = PwdHelper.getPwd(jobNum);
        teacher.setEncrypt_password(SecurityUtil.encryptPassword(pwd));
        teacherService.updateById(teacher);
        return setUpdateResponse();
    }

    /*
    导师修改自己的密码
     */
    @PostMapping("/updatePwd")
    @ResponseBody
    public JSONObject updatePwd(HttpServletRequest request,String pwd){
        Teacher teacher = getTeacher(request);
        teacher.setEncrypt_password(SecurityUtil.encryptPassword(pwd));
        teacherService.updateById(teacher);
        return setUpdateResponse();
    }

}
