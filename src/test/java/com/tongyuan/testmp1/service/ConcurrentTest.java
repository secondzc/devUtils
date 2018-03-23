package com.tongyuan.testmp1.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangcy on 2018/3/23
 * 并发测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class ConcurrentTest {
    private  static final Integer threadNum = 20;
    private CountDownLatch startCountDownLatch = new CountDownLatch(1);
    private CountDownLatch endCountDownLatch = new CountDownLatch(threadNum);

    @Test
    public void concurrentTest(){
        for(int i=0;i<threadNum;i++){
            new Thread(new Request()).start();
        }
        Long startTime = System.currentTimeMillis();
        startCountDownLatch.countDown();
        try {
            endCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("执行"+threadNum+"个任务耗时： "+(endTime-startTime)+"ms");
    }

    private class Request implements Runnable{

        @Override
        public void run() {
            try {
                startCountDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invokeMethod();
            endCountDownLatch.countDown();
        }
    }

    protected abstract void invokeMethod();
}
