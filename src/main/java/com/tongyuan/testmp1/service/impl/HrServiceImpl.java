package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.HrMapper;
import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.service.HrService;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class HrServiceImpl extends ServiceImpl<HrMapper,Hr> implements HrService {
}
