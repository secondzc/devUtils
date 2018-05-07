package com.tongyuan.testmp1.service;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by zhangcy on 2018/4/6
 */
public interface ExcelService {
    void parse(InputStream inputStream) throws RuntimeException;
    void createExcelStream(ServletOutputStream outputStream,String type);
}
