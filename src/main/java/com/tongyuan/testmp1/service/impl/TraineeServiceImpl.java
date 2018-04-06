package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.TraineeMapper;
import com.tongyuan.testmp1.entity.Trainee;
import com.tongyuan.testmp1.service.TraineeService;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class TraineeServiceImpl extends ServiceImpl<TraineeMapper,Trainee> implements TraineeService {
}
