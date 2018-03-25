package com.tongyuan.testmp1;

import com.tongyuan.testmp1.Testmp1Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by zhangcy on 2018/3/25
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Testmp1Application.class);
    }
}
