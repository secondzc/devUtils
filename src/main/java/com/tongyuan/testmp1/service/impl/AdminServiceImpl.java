package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.AdminMapper;
import com.tongyuan.testmp1.entity.Admin;
import com.tongyuan.testmp1.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcy on 2018/4/8
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {
}
