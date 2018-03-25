package com.tongyuan.testmp1.controller;

import com.tongyuan.testmp1.entity.User;
import com.tongyuan.testmp1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangcy on 2018/3/24
 */
@Controller
@RequestMapping("/user")
public class UserController {
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
        return "index";
    }

    @GetMapping("/users")
    @ResponseBody
    public String uesrs(){
        System.out.println("hello users");
        List<User> users = userService.selectPageByAge();
        return users.toString();
    }
}
