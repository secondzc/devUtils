package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangcy on 2018/3/24
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
        return setQueryResponse(userService.selectPageByAge(page,limit));
    }

    @GetMapping("/log")
    @ResponseBody
    public String getLog(){
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        return "zcy log";
    }
}
