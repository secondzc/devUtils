package com.tongyuan.testmp1.helper;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by zhangcy on 2018/5/7
 */
public class XmlHelper {
    public static void parse(String xmlFilePath) {
        Document doc = Dom4jUtil.load(xmlFilePath);
        Element root = doc.getRootElement();
        String rootName = root.getName();
        // TODO: 2018/5/8  
    }

    public static void main(String[] args) {
        parse("/Users/zhangcy/Documents/test/myxml.txt");
    }
}
