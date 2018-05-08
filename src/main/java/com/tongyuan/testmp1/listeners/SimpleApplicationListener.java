package com.tongyuan.testmp1.listeners;

import com.tongyuan.testmp1.helper.DbHelper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by zhangcy on 2018/5/7
 */
public class SimpleApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationReadyEvent){
            //若不存在表，则新建表
            //为1代表外键约束有效
            String createSql = "CREATE TABLE pepole("
                         + "name varchar(10) not null,"
                         + "age int(4) not null,"
                         + "username varchar(10) not null,"
                         + "password varchar(20) not null"
                         + ")charset=utf8;";
            DbHelper.createTable(createSql);
        }
    }
}
