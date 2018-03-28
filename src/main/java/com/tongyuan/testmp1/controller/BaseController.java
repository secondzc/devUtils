package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.helper.PageDataResult;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangcy on 2018/3/28
 */
public class BaseController {
    protected JSONObject setSuccessResponse(Object result){
        JSONObject jo = new JSONObject();
        if(result instanceof List<?>){
            jo.put("total",((List<?>) result).size());
            jo.put("list",result);
        }else if(result instanceof PageDataResult<?>){
            jo.put("total",((PageDataResult<?>)result).getTotals());
            jo.put("list",((PageDataResult<?>)result).getList());
        }else{
            jo.put("data",result);
        }
        //状态码，200 404等
        jo.put("code",200);
        //逻辑上表示是否成功
        jo.put("flag",true);
        return jo;
    }
}
