package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.HrMapper;
import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.helper.PageHandler;
import com.tongyuan.testmp1.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class HrServiceImpl extends ServiceImpl<HrMapper,Hr> implements HrService {
    @Autowired
    private HrMapper hrMapper;
    @Override
    public PageDataResult<Hr> selectHrPage(Integer page, Integer limit) {
        return new PageHandler<Hr>(){
            @Override
            protected List<Hr> doQuery() {
                return hrMapper.selectList(null);
            }
        }.getResult(page,limit);
    }
}
