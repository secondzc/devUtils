package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.TraineeMapper;
import com.tongyuan.testmp1.entity.StudentInfo;
import com.tongyuan.testmp1.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class TraineeServiceImpl extends ServiceImpl<TraineeMapper,StudentInfo> implements TraineeService {
    @Autowired
    private TraineeMapper traineeMapper;

    @Override
    public List<StudentInfo> selectByTeacherNum(String jobNum){
        return traineeMapper.selectList(
             new EntityWrapper<StudentInfo>().eq("job_number",jobNum));
    }
}
