package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tongyuan.testmp1.aop.Permission;
import com.tongyuan.testmp1.dao.StumessageMapper;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.entity.Stumessage;
import com.tongyuan.testmp1.helper.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * Created by zhangcy on 2018/4/10
 */
@RequestMapping("/message")
@Controller
public class StumessageController extends BaseController{
    @Autowired
    private StumessageMapper stumessageMapper;

    /*
    学生新增留言
     */
    @Permission("student")
    @PostMapping("/add")
    @ResponseBody
    public JSONObject insert(HttpServletRequest request,Stumessage stumessage){
        Token stuinfo = getStudentToken(request);
        stumessage.setStuid(stuinfo.getId());
        stumessageMapper.insert(stumessage);
        return setInsertResponse();
    }
    /*
    学生修改留言
     */
    @Permission("student")
    @PostMapping("/update")
    @ResponseBody
    public JSONObject updadte(Stumessage stumessage){
        stumessageMapper.updateById(stumessage);
        return setUpdateResponse();
    }
    /*
    学生查看留言
     */
    @Permission("student")
    @GetMapping("/select")
    @ResponseBody
    public JSONObject select(HttpServletRequest request){
        Token stuinfo = getStudentToken(request);
        List<Stumessage> stumessageList = stumessageMapper.selectList(new EntityWrapper<Stumessage>().eq("stuid",stuinfo.getId()));
        return setQueryResponse(stumessageList);
    }

    /*
    导师或hr查看留言
     */
    @Permission("hr,teacher")
    @GetMapping("/selectByOthers")
    @ResponseBody
    public JSONObject selectByOthers(Integer id){
        List<Stumessage> stumessageList = stumessageMapper.selectList(new EntityWrapper<Stumessage>().eq("stuid",id));
        return setQueryResponse(stumessageList);
    }

    /*
    学生删除留言
     */
    @Permission("student")
    @GetMapping("/delete")
    @ResponseBody
    public JSONObject delete(String id){
        String[] ids = id.split(",");
        for(String one:ids){
            stumessageMapper.deleteById(Integer.valueOf(one));
        }
        return setBatchDeleteResponse();
    }

}
