package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tongyuan.testmp1.aop.Permission;
import com.tongyuan.testmp1.dao.PlandetailMapper;
import com.tongyuan.testmp1.dao.StuplanMapper;
import com.tongyuan.testmp1.entity.Plandetail;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.entity.Stuplan;
import com.tongyuan.testmp1.helper.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Permission("student")
    @GetMapping("/selectByStu")
    @ResponseBody
    public JSONObject selectByStu(HttpServletRequest request,Integer month){
        Token stuinfo = getStudentToken(request);
        List<Stuplan> stuplanList = stuplanMapper.selectList(new EntityWrapper<Stuplan>().
                eq("stuid",stuinfo.getId()).eq("month",month));
        if(stuplanList.isEmpty()){
            return setQueryResponse(null);
        }else{
            return setQueryResponse(stuplanList.get(0));
        }
    }
    /*
    学生查看培养计划详情
     */
    @Permission("student")
    @GetMapping("/selectDetailByStu")
    @ResponseBody
    public JSONObject selectDetailByStu(HttpServletRequest request,Integer month){
        Token stuinfo = getStudentToken(request);
        List<Plandetail> plandetailList = plandetailMapper.selectList(
                new EntityWrapper<Plandetail>().eq("stuid",stuinfo.getId()).eq("month",month));
        return setQueryResponse(plandetailList);
    }

    /*
    导师新增培养计划
     */
    @Permission("teacher")
    @PostMapping("/addPlan")
    @ResponseBody
    public JSONObject addPlan(Stuplan stuplan){
        stuplanMapper.insert(stuplan);
        return setInsertResponse();
    }
    /*
    导师新增培养计划详情/pla/
     */
    @Permission("teacher")
    @PostMapping("/addDetail")
    @ResponseBody
    public JSONObject addDetail(Plandetail plandetail){
        plandetailMapper.insert(plandetail);
        return setInsertResponse();
    }

    /*
    导师删除培养计划
     */
    @Permission("teacher")
    @GetMapping("/deletePlan")
    @ResponseBody
    public JSONObject deletePlan(String id){
        String[] ids = id.split(",");
        for(String one:ids){
            stuplanMapper.deleteById(Integer.valueOf(one));
        }
        return setBatchDeleteResponse();
    }
    /*
    导师删除培养计划详情
     */
    @Permission("teacher")
    @GetMapping("/deletePlandetail")
    @ResponseBody
    public JSONObject deletePlandetail(String id){
        String[] ids = id.split(",");
        for(String one:ids){
            plandetailMapper.deleteById(Integer.valueOf(one));
        }
        return setBatchDeleteResponse();
    }

    /*
    导师修改培养计划
     */
    @Permission("teacher")
    @PostMapping("/updatePlan")
    @ResponseBody
    public JSONObject updatePlan(Stuplan stuplan){
        stuplanMapper.updateById(stuplan);
        return setUpdateResponse();
    }
    /*
    导师修改培养计划详情
     */
    @Permission("teacher")
    @PostMapping("/updateDetail")
    @ResponseBody
    public JSONObject updateDetail(Plandetail plandetail){
        plandetailMapper.updateById(plandetail);
        return setUpdateResponse();
    }
    /*
    导师或hr查看培养计划
     */
    @Permission("teacher,hr")
    @GetMapping("/selectPlanByOthers")
    @ResponseBody
    public JSONObject selectPlanByOthers(Integer stuid,Integer month){
        List<Stuplan> stuplanList = stuplanMapper.selectList(new EntityWrapper<Stuplan>().
                eq("stuid",stuid).eq("month",month));
        if(stuplanList.isEmpty()){
            return setQueryResponse(null);
        }else{
            return setQueryResponse(stuplanList.get(0));
        }
    }
    /*
    导师或hr查看培养计划详情
     */
    @Permission("teacher,hr")
    @GetMapping("/selectDetailByOthers")
    @ResponseBody
    public JSONObject selectDetailByOthers(Integer stuid,Integer month){
        List<Plandetail> plandetailList = plandetailMapper.selectList(
                new EntityWrapper<Plandetail>().eq("stuid",stuid).eq("month",month));
        return setQueryResponse(plandetailList);
    }

    /*
    导师新增或更新培养计划
     */
    @Permission("teacher")
    @PostMapping("/cuPlan")
    @ResponseBody
    public JSONObject cuPlan(Stuplan stuplan){
        Integer month = stuplan.getMonth();
        Integer stuid = stuplan.getStuid();
        List<Stuplan> stuplanList = stuplanMapper.selectList(new EntityWrapper<Stuplan>().eq("month",month).eq("stuid",stuid));
        if(stuplanList.size()==0){
            //插入
            stuplanMapper.insert(stuplan);
        }else if(stuplanList.size()==1){
            //修改
            Integer id = stuplanList.get(0).getId();
            stuplan.setId(id);
            stuplanMapper.updateById(stuplan);
        }else {
            throw new RuntimeException("unexpected stuplan number");
        }
        return setInsertResponse();
    }

}
