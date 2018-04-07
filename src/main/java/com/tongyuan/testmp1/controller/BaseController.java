package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.helper.PageDataResult;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangcy on 2018/3/28
 */
public class BaseController {
    protected JSONObject setQueryResponse(Object result){
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
        jo.put("code",0);
        jo.put("error",0);
        jo.put("msg","查询结果!");
        jo.put("url","");
        return jo;
    }

    protected JSONObject setDeleteResponse(Integer count){
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("count",count);
        jo.put("data",null);
        jo.put("error",0);
        jo.put("msg","成功删除"+count+"条数据!");
        jo.put("url","");
        return jo;
    }
}
