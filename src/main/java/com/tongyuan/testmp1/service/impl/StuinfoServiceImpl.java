package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.StuinfoMapper;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.service.StuinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class StuinfoServiceImpl extends ServiceImpl<StuinfoMapper,Stuinfo> implements StuinfoService {
    @Autowired
    private StuinfoMapper stuinfoMapper;

    @Override
    public List<Stuinfo> selectByTeacherNum(String teacherJobNum){
        return stuinfoMapper.selectList(
             new EntityWrapper<Stuinfo>().eq("teacher_job_number",teacherJobNum));
    }

    @Override
    public List<Stuinfo> selectByDept(String firstDept, String secondDept) {
        return stuinfoMapper.selectList(
                new EntityWrapper<Stuinfo>().eq("first_dept",firstDept).eq("second_dept",secondDept));
    }
}
