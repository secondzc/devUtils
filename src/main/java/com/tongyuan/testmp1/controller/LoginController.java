package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tongyuan.testmp1.entity.*;
import com.tongyuan.testmp1.helper.Token;
import com.tongyuan.testmp1.service.AdminService;
import com.tongyuan.testmp1.service.HrService;
import com.tongyuan.testmp1.service.StuinfoService;
import com.tongyuan.testmp1.service.TeacherService;
import com.tongyuan.testmp1.util.SecurityUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangcy on 2018/4/7
 */
@RequestMapping("/")
@Controller
public class LoginController extends BaseController{
    @Autowired
    private HrService hrService;
    @Autowired
    private StuinfoService stuinfoService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;


    @PostMapping("/toLogin")
    @ResponseBody
    public JSONObject login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String encrypt_pwd = SecurityUtil.encryptPassword(password);
        Map<String,Object> map = new HashMap<>();
        map.put("job_number",username);
        map.put("encrypt_password",encrypt_pwd);

        JSONObject jo = new JSONObject();
        List<Stuinfo> stuinfoList = stuinfoService.selectByMap(map);
        if(!stuinfoList.isEmpty()){
            Stuinfo info = stuinfoList.get(0);
            request.getSession().setAttribute("user",new Token(info.getId(),info.getJob_number(),info.getEncrypt_password()));
            request.getSession().setAttribute("type","student");
            jo.put("flag",true);
            jo.put("type","student");
            jo.put("name",stuinfoList.get(0).getName());
            return jo;
        }

        List<Hr> hrList = hrService.selectByMap(map);
        if(!hrList.isEmpty()){
            request.getSession().setAttribute("user",hrList.get(0));
            request.getSession().setAttribute("type","hr");
            jo.put("flag",true);
            jo.put("type","hr");
            jo.put("name",hrList.get(0).getName());
            return jo;
        }

        List<Teacher> teacherList = teacherService.selectByMap(map);
        if(!teacherList.isEmpty()){
            request.getSession().setAttribute("user",teacherList.get(0));
            request.getSession().setAttribute("type","teacher");
            jo.put("flag",true);
            jo.put("type","teacher");
            jo.put("name",teacherList.get(0).getName());
            return jo;
        }

        List<Admin> adminList = adminService.selectByMap(map);
        if(!adminList.isEmpty()){
            request.getSession().setAttribute("user",adminList.get(0));
            request.getSession().setAttribute("type","admin");
            jo.put("flag",true);
            jo.put("type","admin");
            jo.put("name",adminList.get(0).getJob_number());
            return jo;
        }
        jo.put("flag",false);
        jo.put("type","");
        jo.put("name","");
        return jo;
    }

    @GetMapping("/logout")
    @ResponseBody
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("type");
        return setSuccessResponse();
    }
}
