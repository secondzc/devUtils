package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tongyuan.testmp1.dao.PlandetailMapper;
import com.tongyuan.testmp1.dao.StuplanMapper;
import com.tongyuan.testmp1.entity.Plandetail;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.entity.Stuplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcy on 2018/4/10
 */
@RequestMapping("/plan")
@Controller
public class PlanController extends BaseController{
    @Autowired
    private StuplanMapper stuplanMapper;
    @Autowired
    private PlandetailMapper plandetailMapper;
    /*
    学生查看培养计划
     */
    @GetMapping("/selectByStu")
    @ResponseBody
    public JSONObject selectByStu(HttpServletRequest request,Integer month){
        //return setQueryResponse("this is target"+month);
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        List<Stuplan> stuplanList = stuplanMapper.selectList(new EntityWrapper<Stuplan>().
                eq("stuid",stuinfo.getId()).eq("month",month));
        return setQueryResponse(stuinfo);
    }
    /*
    学生查看培养计划详情
     */
    @GetMapping("/selectDetailByStu")
    @ResponseBody
    public JSONObject selectDetailByStu(HttpServletRequest request,Integer month){
//        Plandetail plandetail = new Plandetail();
//        plandetail.setPeriod("period"+month);
//        plandetail.setInspect("inspect");
//        plandetail.setKnowledge("knowledge");
//        plandetail.setMaterial("material");
//        plandetail.setMonth(month);
//        plandetail.setId(1);
//        plandetail.setId(1);
//        List<Plandetail> plandetails = new ArrayList<>();
//        plandetails.add(plandetail);
//        return setQueryResponse(plandetails);
        Stuinfo stuinfo = (Stuinfo)request.getSession().getAttribute("user");
        List<Plandetail> plandetailList = plandetailMapper.selectList(
                new EntityWrapper<Plandetail>().eq("stuid",stuinfo.getId()).eq("month",month));
        return setQueryResponse(plandetailList);
    }

}
