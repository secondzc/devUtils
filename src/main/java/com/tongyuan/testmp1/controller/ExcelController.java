package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.aop.Permission;
import com.tongyuan.testmp1.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zhangcy on 2018/4/3
 */
@RequestMapping("/excel")
@Controller
public class ExcelController extends BaseController{

    @Autowired
    private ExcelService excelService;

    @Permission("admin")
    @PostMapping("/upload")
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file){
        //long startTime = System.currentTimeMillis();
        JSONObject jo = new JSONObject();
        try{
            byte[] bytes = file.getBytes();
            excelService.parse(new ByteArrayInputStream(bytes));
            long endTime = System.currentTimeMillis();
            //System.out.println("上传耗时 "+(endTime-startTime)+" ms");
            jo.put("code",0);
            jo.put("msg","excel解析成功");
            return jo;
        }catch (Exception e){
            jo.put("code",1);
            jo.put("msg","excel解析失败");
            return jo;
        }
    }
}
