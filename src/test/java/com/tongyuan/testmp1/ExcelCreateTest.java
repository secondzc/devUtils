package com.tongyuan.testmp1;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhangcy on 2018/4/20
 */
public class ExcelCreateTest {
    /*
    产生一张实习生表
     */
    public static void createExcel() throws Exception{
        OutputStream out = null;
        try{

            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("/Users/zhangcy/Documents/test/信息表.xlsx"));
            //获取第一个工作表：两种方式
            XSSFSheet sheet = workbook.getSheet("学生信息表");
            int rowNumber = sheet.getLastRowNum();  // 第一行从0开始算
            //System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }

            String str = "zddas这是fdds大of字符ijas随机测试dfoi传asdjf";
            for(int i=0;i<1000;i++){
                Row row = sheet.createRow(i+1);

                int index = (int) Math.round(Math.random()*(str.length()-8));
                String str1 = str.substring(index,index+6);
                for(int j=0;j<4;j++){
                    //随机字符串
                    Cell cell = row.createCell(j);
                    cell.setCellValue(str1);
                }
                Cell cell5 = row.createCell(4);
                cell5.setCellValue("420822199501015151");
                Cell cell6 = row.createCell(5);
                cell6.setCellValue("thisisme@qq.com");
                for(int j=6;j<12;j++){
                    //随机字符串
                    Cell cell = row.createCell(j);
                    cell.setCellValue(str1);
                }

                CellStyle style = workbook.createCellStyle();
                CreationHelper createHelper = workbook.getCreationHelper();
                style.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));

                //随机日期
                Cell cell1 = row.createCell(12);
                cell1.setCellValue(new java.util.Date());
                cell1.setCellStyle(style);

                for(int j=13;j<19;j++){
                    //随机字符串
                    Cell cell = row.createCell(j);
                    cell.setCellValue(str1);
                }
                //date
                //随机日期
                Cell cell2 = row.createCell(19);
                cell2.setCellValue(new java.util.Date());
                cell2.setCellStyle(style);
                //string
                Cell cell3 = row.createCell(20);
                cell3.setCellValue(str1);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream("/Users/zhangcy/Documents/test/信息表.xlsx");
            workbook.write(out);

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        try {
            createExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
