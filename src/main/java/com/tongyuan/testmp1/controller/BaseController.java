package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.entity.Teacher;
import com.tongyuan.testmp1.exception.AccessDeniedException;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.helper.Token;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String joString = JSONObject.toJSONStringWithDateFormat(jo,"yyyy-MM-dd", SerializerFeature.PrettyFormat);
        return JSONObject.parseObject(joString);
    }

    protected JSONObject setUpdateResponse(){
        JSONObject jo = new JSONObject();
        jo.put("count",0);
        jo.put("code",0);
        jo.put("data",null);
        jo.put("error",0);
        jo.put("msg","更新数据成功!");
        jo.put("url","");
        return jo;
    }

    protected JSONObject setInsertResponse(){
        JSONObject jo = new JSONObject();
        jo.put("count",0);
        jo.put("code",0);
        jo.put("data",null);
        jo.put("error",0);
        jo.put("msg","增加数据成功!");
        jo.put("url","");
        return jo;
    }

    protected JSONObject setBatchDeleteResponse(){
        JSONObject jo = new JSONObject();
        jo.put("count",0);
        jo.put("code",0);
        jo.put("data",null);
        jo.put("error",0);
        jo.put("msg","删除数据成功!");
        jo.put("url","");
        return jo;
    }

    protected Token getStudentToken(HttpServletRequest request){
        return (Token)request.getSession().getAttribute("user");
    }

    protected Hr getHr(HttpServletRequest request){
        return (Hr)request.getSession().getAttribute("user");
    }

    protected Teacher getTeacher(HttpServletRequest request){
        return (Teacher) request.getSession().getAttribute("user");
    }

    @ExceptionHandler
    public void handler(HttpServletResponse response,Exception e) throws Exception{
        if(e instanceof AccessDeniedException){
            response.sendRedirect("/noPermission");
        }else {
            response.sendRedirect("/myError");
        }
    }
}
