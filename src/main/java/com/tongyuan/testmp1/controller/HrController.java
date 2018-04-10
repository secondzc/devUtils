package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.dao.ViewMapper;
import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.service.HrService;
import com.tongyuan.testmp1.service.ViewService;
import com.tongyuan.testmp1.viewModel.StuTeacherView;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangcy on 2018/4/8
 */
@RequestMapping("/hr")
@Controller
public class HrController extends BaseController{
    @Autowired
    private HrService hrService;

    /*
    admin新增hr
     */
    @PostMapping("/add")
    @ResponseBody
    public JSONObject add(Hr hr){
        String psw = hr.getJob_number().substring(hr.getJob_number().length()-6);
        hr.setEncrypt_password(psw);
        hrService.insert(hr);
        return setInsertResponse();
    }

    /*
    admin分页查询所有hr
     */
    @GetMapping("/select")
    @ResponseBody
    public JSONObject select(@RequestParam("page")Integer page,
                             @RequestParam("limit")Integer limit){
        PageDataResult<Hr> result = hrService.selectHrPage(page,limit);
        return setQueryResponse(result);
    }

    /*
    admin修改hr
     */
    @PostMapping("/update")
    @ResponseBody
    public JSONObject update(Hr hr){
        hrService.updateById(hr);
        return setUpdateResponse();
    }

    /*
    admin删除hr
     */
    @PostMapping("/delete")
    @ResponseBody
    public JSONObject delete(Hr hr){
        return setDeleteResponse(hrService.deleteById(hr.getId()));
    }
}
