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

    @GetMapping("/select")
    @ResponseBody
    public JSONObject selectByStudent(HttpServletRequest request,@RequestParam("month") Integer month){
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        String job_number = stuinfo.getJob_number();
        List<Stusummary> stusummaryList = stusummaryService.selectList(
                new EntityWrapper<Stusummary>().eq("month",month).eq("job_number",job_number));
        return setQueryResponse(stusummaryList);
    }

    @GetMapping("/selectByOthers")
    @ResponseBody
    public JSONObject selectByOthers(@RequestParam("job_number") String job_number,@RequestParam("month") Integer month){
        List<Stusummary> stusummaryList = stusummaryService.selectList(
                new EntityWrapper<Stusummary>().eq("month",month).eq("job_number",job_number));
        return setQueryResponse(stusummaryList);
    }

    @PostMapping("/add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request,Stusummary stusummary){
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        String job_number = stuinfo.getJob_number();
        stusummary.setJob_number(job_number);
        stusummaryService.insert(stusummary);
        return setInsertResponse();
    }

    @PostMapping("/update")
    @ResponseBody
    public JSONObject update(HttpServletRequest request,Stusummary stusummary){
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        String job_number = stuinfo.getJob_number();
        Integer id = stusummaryService.selectIdByJobNumber(job_number);
        stusummary.setId(id);
        stusummaryService.updateById(stusummary);
        return setUpdateResponse();
    }
}
