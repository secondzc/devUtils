package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.StusummaryMapper;
import com.tongyuan.testmp1.entity.Stusummary;
import com.tongyuan.testmp1.service.StusummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class StusummaryServiceImpl extends ServiceImpl<StusummaryMapper,Stusummary> implements StusummaryService {
    @Autowired
    private StusummaryMapper stusummaryMapper;
    @Override
    public Integer selectIdByJobNumber(String job_number) {
        return stusummaryMapper.selectList(new EntityWrapper<Stusummary>().eq("job_number",job_number)).get(0).getId();
    }
}
