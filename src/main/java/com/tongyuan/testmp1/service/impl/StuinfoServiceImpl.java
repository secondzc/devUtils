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
    private StuinfoMapper traineeMapper;

    @Override
    public List<Stuinfo> selectByTeacherNum(String jobNum){
        return traineeMapper.selectList(
             new EntityWrapper<Stuinfo>().eq("job_number",jobNum));
    }
}
