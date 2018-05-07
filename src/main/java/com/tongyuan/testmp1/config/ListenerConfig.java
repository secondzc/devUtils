package com.tongyuan.testmp1.config;

import com.tongyuan.testmp1.listeners.SimpleApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangcy on 2018/5/7
 */
@Configuration
public class ListenerConfig {

    @Bean
    public SimpleApplicationListener applicationStartListener(){
        return new SimpleApplicationListener();
    }
}
