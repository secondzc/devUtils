package com.tongyuan.testmp1.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Created by zhangcy on 2018/3/26
 */
public class ExcelReaderUtil {

    public static void main(String[] args) {
        //需要解析的excel文件
        File file = new File("/Users/zhangcy/Documents/test/test.xls");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            //获取第一个工作表：两种方式
            //HSSFSheet sheet = workbook.getSheet("Sheet0");
            //读取默认的第一个sheet页
            HSSFSheet sheet = workbook.getSheetAt(0);

            int firstRowNum = sheet.getFirstRowNum();
            //System.out.println("" + firstRowNum);
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            for(int i = firstRowNum; i < lastRowNum; i++){
                HSSFRow row = sheet.getRow(i);

                int firstCellNum = row.getFirstCellNum();
                //获取当前行最后一个单元格列号
                int lastCellNum = row.getLastCellNum();
                for(int j = firstCellNum; j < lastCellNum; j++){
                    HSSFCell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
