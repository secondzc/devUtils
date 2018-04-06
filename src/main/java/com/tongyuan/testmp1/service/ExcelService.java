package com.tongyuan.testmp1.service;

import java.io.InputStream;

/**
 * Created by zhangcy on 2018/4/6
 */
public interface ExcelService {
    void parse(InputStream inputStream) throws RuntimeException;
}
