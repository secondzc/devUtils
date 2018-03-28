package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tongyuan.testmp1.entity.User;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.helper.PageHandler;
import com.tongyuan.testmp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangcy on 2018/3/24
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello zcy");
        return "hello zcy";
    }

    @GetMapping("/index")
    public String toIndex(){
        System.out.println("hello index");
        return "demo";
    }

    @GetMapping("/users")
    @ResponseBody
    public JSONObject uesrs(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit){
        System.out.println("hello users");
        return setSuccessResponse(userService.selectPageByAge(page,limit));
    }
}
