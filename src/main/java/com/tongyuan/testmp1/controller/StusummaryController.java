package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.entity.Stusummary;
import com.tongyuan.testmp1.service.StusummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangcy on 2018/4/8
 */
@RequestMapping("/stusummary")
@Controller
public class StusummaryController extends BaseController{
    @Autowired
    private StusummaryService stusummaryService;

    /*
    学生查看自己的总结
     */
    @GetMapping("/select")
    @ResponseBody
    public JSONObject selectByStudent(HttpServletRequest request,@RequestParam("month") Integer month){
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        List<Stusummary> stusummaryList = stusummaryService.selectList(
                new EntityWrapper<Stusummary>().eq("month",month).eq("stuid",stuinfo.getId()));
        if(stusummaryList.isEmpty()){
            return setQueryResponse(null);
        }else{
            return setQueryResponse(stusummaryList.get(0));
        }

    }

    /*
    hr或导师查看学生的总结
     */
    @GetMapping("/selectByOthers")
    @ResponseBody
    public JSONObject selectByOthers(@RequestParam("stuid") Integer stuid,@RequestParam("month") Integer month){
        List<Stusummary> stusummaryList = stusummaryService.selectList(
                new EntityWrapper<Stusummary>().eq("month",month).eq("stuid",stuid));
        if(stusummaryList.isEmpty()){
            return setQueryResponse(null);
        }else{
            return setQueryResponse(stusummaryList.get(0));
        }
    }

    /*
    学生新增总结
     */
    @PostMapping("/add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,Stusummary stusummary){
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        stusummary.setStuid(stuinfo.getId());
        stusummaryService.insert(stusummary);
        return setInsertResponse();
    }

    /*
     学生更新总结
     */
    @PostMapping("/update")
    @ResponseBody
    public JSONObject update(Stusummary stusummary){
        stusummaryService.updateById(stusummary);
        return setUpdateResponse();
    }
}
