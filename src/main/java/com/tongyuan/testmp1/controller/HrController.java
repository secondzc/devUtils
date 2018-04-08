package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.dao.UnionMapper;
import com.tongyuan.testmp1.entity.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangcy on 2018/4/8
 */
@RequestMapping("/hr")
@Controller
public class HrController {

    @Autowired
    private UnionMapper unionMapper;

//    @PostMapping("/students")
//    @ResponseBody
//    public JSONObject showAllStudents(HttpServletRequest request){
//        Hr hr = (Hr)request.getSession().getAttribute("user");
//
//    }
}
