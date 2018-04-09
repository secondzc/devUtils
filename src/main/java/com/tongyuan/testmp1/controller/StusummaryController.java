package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.entity.Stusummary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangcy on 2018/4/8
 */
@RequestMapping("/stusummary")
@Controller
public class StusummaryController extends BaseController{

    @GetMapping("/select")
    @ResponseBody
    public JSONObject selectSummary(HttpServletRequest request, Stusummary stusummary){
        return setQueryResponse(null);
    }
}
