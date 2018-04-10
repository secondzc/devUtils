package com.tongyuan.testmp1.service;

import com.baomidou.mybatisplus.service.IService;
import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.helper.PageDataResult;

/**
 * Created by zhangcy on 2018/4/6
 */
public interface HrService extends IService<Hr> {
    /*
    分页查找所有hr
     */
    PageDataResult<Hr> selectHrPage(Integer page,Integer limit);
}
