package com.tongyuan.testmp1.controller;

import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.service.StuinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangcy on 2018/4/9
 */
@RequestMapping("/stuinfo")
@Controller
public class StuinfoController {
    @Autowired
    private StuinfoService stuinfoService;

    @GetMapping("/update")
    @ResponseBody
    public void update(Stuinfo stuinfo){

    }
}
