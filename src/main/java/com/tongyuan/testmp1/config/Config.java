package com.tongyuan.testmp1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhangcy on 2018/2/15
 */
@Configuration
@ImportResource(locations = {"classpath:dataSource.xml"})
public class Config {
}
