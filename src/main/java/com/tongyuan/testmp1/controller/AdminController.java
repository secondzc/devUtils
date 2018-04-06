package com.tongyuan.testmp1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zhangcy on 2018/4/3
 */
@RequestMapping("/admin")
@Controller
public class AdminController extends BaseController{

    @GetMapping("/test")
    public String test(){
        return "administrator";
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file){
        try{
            byte[] bytes = file.getBytes();
        }catch (IOException e){
            throw new RuntimeException("上传文件失败");
        }
    }
}
