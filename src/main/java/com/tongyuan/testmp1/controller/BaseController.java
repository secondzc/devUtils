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
            jo.put("count",((List<?>) result).size());
            jo.put("data",result);
        }else if(result instanceof PageDataResult<?>){
            jo.put("count",((PageDataResult<?>)result).getTotals());
            jo.put("data",((PageDataResult<?>)result).getList());
        }else{
            jo.put("data",result);
        }
        //状态码，200 404等
        jo.put("code",0);
        //逻辑上表示是否成功
        jo.put("flag",true);

        jo.put("error",0);
        jo.put("msg","");
        jo.put("url","");
        return jo;
    }
}
