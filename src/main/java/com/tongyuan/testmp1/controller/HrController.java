package com.tongyuan.testmp1.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.testmp1.aop.Permission;
import com.tongyuan.testmp1.dao.ViewMapper;
import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.helper.PwdHelper;
import com.tongyuan.testmp1.service.HrService;
import com.tongyuan.testmp1.service.ViewService;
import com.tongyuan.testmp1.util.SecurityUtil;
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
    @Permission("admin")
    @PostMapping("/add")
    @ResponseBody
    public JSONObject add(Hr hr){
        String psw = PwdHelper.getPwd(hr.getJob_number());
        hr.setEncrypt_password(SecurityUtil.encryptPassword(psw));
        hrService.insert(hr);
        return setInsertResponse();
    }

    /*
    admin分页查询所有hr
     */
    @Permission("admin")
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
    @Permission("admin")
    @PostMapping("/update")
    @ResponseBody
    public JSONObject update(Hr hr){
        hrService.updateById(hr);
        return setUpdateResponse();
    }

    /*
    admin批量删除hr
     */
    @Permission("admin")
    @GetMapping("/batchDelete")
    @ResponseBody
    public JSONObject batchDelete(String id){
        String[] ids = id.split(",");
        for(String one:ids){
            hrService.deleteById(Integer.valueOf(one));
        }
        return setBatchDeleteResponse();
    }

    /*
    admin重置hr密码
     */
    @Permission("admin")
    @GetMapping("/resetPwd")
    @ResponseBody
    public JSONObject resetPwd(Integer id){
        Hr hr = hrService.selectById(id);
        String jobNum = hr.getJob_number();
        String pwd = PwdHelper.getPwd(jobNum);
        hr.setEncrypt_password(SecurityUtil.encryptPassword(pwd));
        hrService.updateById(hr);
        return setUpdateResponse();
    }

    /*
    hr修改自己的密码
     */
    @Permission("hr")
    @PostMapping("/updatePwd")
    @ResponseBody
    public JSONObject updatePwd(HttpServletRequest request,String password){
        Hr hr = getHr(request);
        hr.setEncrypt_password(SecurityUtil.encryptPassword(password));
        hrService.updateById(hr);
        return setUpdateResponse();
    }
}
