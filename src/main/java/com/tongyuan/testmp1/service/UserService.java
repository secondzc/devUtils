package com.tongyuan.testmp1.service;

import com.baomidou.mybatisplus.service.IService;
import com.tongyuan.testmp1.entity.Userr;
import com.tongyuan.testmp1.helper.PageDataResult;

/**
 * Created by zhangcy on 2018/2/15
 */
public interface UserService extends IService<Userr>{
    PageDataResult<Userr> selectPageByAge(Integer page, Integer limit);
    void testTrans();
}
