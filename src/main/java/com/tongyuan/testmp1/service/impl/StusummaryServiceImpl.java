package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.StudentSummaryMapper;
import com.tongyuan.testmp1.entity.StudentSummary;
import com.tongyuan.testmp1.service.StudentSummaryService;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class StudentSummaryServiceImpl extends ServiceImpl<StudentSummaryMapper,StudentSummary> implements StudentSummaryService {
}
