package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.entity.Stusummary;
import com.tongyuan.testmp1.helper.Token;
import com.tongyuan.testmp1.service.StusummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Token stuinfo = (Token)request.getSession().getAttribute("user");
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
        Token stuinfo = (Token)request.getSession().getAttribute("user");
        stusummary.setStuid(stuinfo.getId());
        stusummaryService.insert(stusummary);
        return setInsertResponse();
    }

    /*
     学生更新总结
     */
    @PostMapping("/update")
    @ResponseBody
    public JSONObject update(HttpServletRequest request,Stusummary stusummary){
        Integer month = stusummary.getMonth();
        Token token = (Token) request.getSession().getAttribute("user");
        Integer stuid = token.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("month",month);
        map.put("stuid",stuid);
        List<Stusummary> stusummaryList = stusummaryService.selectByMap(map);
        Integer summaryId =0;
        if(!stusummaryList.isEmpty() && stusummaryList.size()==1){
            summaryId = stusummaryList.get(0).getId();
        }
        stusummary.setId(summaryId);
        stusummaryService.updateById(stusummary);
        return setUpdateResponse();
    }

    /*
    学生删除总结
     */
    @GetMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        String[] ids = id.split(",");
        for(String one:ids){
            stusummaryService.deleteById(Integer.valueOf(one));
        }
        return setBatchDeleteResponse();
    }

    /*
    学生新增或更新总结
     */
    @PostMapping("/cuSummary")
    @ResponseBody
    public JSONObject cuSummary(HttpServletRequest  request,Stusummary stusummary){
        Token token = (Token)request.getSession().getAttribute("user");
        Integer month = stusummary.getMonth();
        Integer stuid = token.getId();
        List<Stusummary> stusummaryList = stusummaryService.selectList(new EntityWrapper<Stusummary>().eq("month",month).eq("stuid",stuid));
        if(stusummaryList.size()==0){
            stusummaryService.insert(stusummary);
        }else if(stusummaryList.size()==1){
            Integer id = stusummaryList.get(0).getId();
            stusummary.setId(id);
            stusummaryService.updateById(stusummary);
        }else{
            throw new RuntimeException("unexpected stusummary number");
        }
        return setInsertResponse();
    }

}
