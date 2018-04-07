package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangcy on 2018/4/7
 */
@RequestMapping("/")
@Controller
public class LoginController {
    @PostMapping("/login")
    public JSONObject login(){
        return  null;
    }
}
