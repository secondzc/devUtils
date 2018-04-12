package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
    @PostMapping("/add")
    @ResponseBody
    public JSONObject insert(Stumessage stumessage){
        stumessageMapper.insert(stumessage);
        return setInsertResponse();
    }
    /*
    学生修改留言
     */
    @PostMapping("/update")
    @ResponseBody
    public JSONObject updadte(Stumessage stumessage){
        stumessageMapper.updateById(stumessage);
        return setUpdateResponse();
    }
    /*
    学生查看留言
     */
    @GetMapping("/select")
    @ResponseBody
    public JSONObject select(HttpServletRequest request){
        Token stuinfo = (Token)request.getSession().getAttribute("user");
        List<Stumessage> stumessageList = stumessageMapper.selectList(new EntityWrapper<Stumessage>().eq("stuid",stuinfo.getId()));
        if(stumessageList.isEmpty()){
            return setQueryResponse(null);
        }else{
            return setQueryResponse(stumessageList.get(0));
        }
    }

    /*
    导师或hr查看留言
     */
    @GetMapping("/selectByOthers")
    @ResponseBody
    public JSONObject selectByOthers(Integer id){
        List<Stumessage> stumessageList = stumessageMapper.selectList(new EntityWrapper<Stumessage>().eq("stuid",id));
        if(stumessageList.isEmpty()){
            return setQueryResponse(null);
        }else{
            return setQueryResponse(stumessageList.get(0));
        }
    }

    /*
    学生删除留言
     */
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
