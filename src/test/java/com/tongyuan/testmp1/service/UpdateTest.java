package com.tongyuan.testmp1.service;

import com.tongyuan.testmp1.dao.OrderDetailMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangcy on 2018/3/23
 * 修改测试
 */
public class UpdateTest extends ConcurrentTest{
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    protected void invokeMethod() {
        orderDetailMapper.decreaseGoodsId();
    }

    @Test
    public void testDecrease(){
        concurrentTest();
    }
}
