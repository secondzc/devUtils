package com.tongyuan.testmp1.service.impl;

import com.tongyuan.testmp1.entity.Hr;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.entity.Teacher;
import com.tongyuan.testmp1.service.ExcelService;
import com.tongyuan.testmp1.util.SecurityUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Timestamp;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public void parse(InputStream inputStream) throws RuntimeException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //获取第一个工作表：两种方式
            XSSFSheet sheet = workbook.getSheet("学生信息表");
            //读取默认的第一个sheet页
            //XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFSheet sheet1 = workbook.getSheet("导师信息表");
            XSSFSheet sheet2 = workbook.getSheet("HR信息表");

            parseStudent(sheet);
            parseTeacher(sheet1);
            parseHr(sheet2);
        } catch (Exception e) {
            throw new RuntimeException("excel解析失败");
        }
    }

    public void parseStudent(XSSFSheet sheet) throws RuntimeException{
        try {
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            for(int i = firstRowNum+1; i <= lastRowNum; i++){
                Stuinfo stuinfo= new Stuinfo();
                XSSFRow row = sheet.getRow(i);
                int j = row.getFirstCellNum();

                XSSFCell name = row.getCell(j++);
                stuinfo.setName(name.getStringCellValue());
                XSSFCell jobNum = row.getCell(j++);
                stuinfo.setJob_umber(getString(jobNum));
                XSSFCell phone = row.getCell(j++);
                stuinfo.setPhone_umber(getString(phone));
                XSSFCell sex = row.getCell(j++);
                stuinfo.setSex(sex.getStringCellValue());
                XSSFCell id = row.getCell(j++);
                stuinfo.setId_umber(getString(id));
                XSSFCell email = row.getCell(j++);
                stuinfo.setEmail_addr(email.getStringCellValue());
                XSSFCell education = row.getCell(j++);
                stuinfo.setEducation(education.getStringCellValue());
                XSSFCell college = row.getCell(j++);
                stuinfo.setGraduate_college(college.getStringCellValue());
                XSSFCell major = row.getCell(j++);
                stuinfo.setMajor(major.getStringCellValue());
                XSSFCell job = row.getCell(j++);
                stuinfo.setJob(job.getStringCellValue());
                XSSFCell jobDir = row.getCell(j++);
                stuinfo.setJob_direction(jobDir.getStringCellValue());
                XSSFCell place = row.getCell(j++);
                stuinfo.setPlace(place.getStringCellValue());
                XSSFCell hireTime = row.getCell(j++);
                stuinfo.setHire_time(new Timestamp(hireTime.getDateCellValue().getTime()));
                XSSFCell firstDept = row.getCell(j++);
                stuinfo.setFirst_dept(firstDept.getStringCellValue());
                XSSFCell secondDept = row.getCell(j++);
                stuinfo.setSecond_dept(secondDept.getStringCellValue());
                XSSFCell hrName = row.getCell(j++);
                stuinfo.setHr_name(hrName.getStringCellValue());
                XSSFCell hrJobNum = row.getCell(j++);
                stuinfo.setHr_job_number(getString(hrJobNum));
                XSSFCell teacherName = row.getCell(j++);
                stuinfo.setTeacher_name(teacherName.getStringCellValue());
                XSSFCell teacherJobNum = row.getCell(j++);
                stuinfo.setTeacher_job_number(getString(teacherJobNum));
                XSSFCell quitTime = row.getCell(j++);
                stuinfo.setQuit_time(new Timestamp(quitTime.getDateCellValue().getTime()));
                XSSFCell quitReason = row.getCell(j++);
                stuinfo.setQuit_reason(quitReason.getStringCellValue());

                //设置初始密码:身份证后六位
                String idcard = getString(id);
                String psw = idcard.substring(idcard.length()-6);
                stuinfo.setEncrypt_password(SecurityUtil.encryptPassword(psw));
                System.out.println(stuinfo);
            }
        } catch (Exception e) {
            throw new RuntimeException("excel解析失败");
        }
    }
    public void parseTeacher(XSSFSheet sheet) throws RuntimeException{
        try {
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            for(int i = firstRowNum+1; i <= lastRowNum; i++){
                Teacher teacher = new Teacher();
                XSSFRow row = sheet.getRow(i);
                int j = row.getFirstCellNum();

                XSSFCell firstDept = row.getCell(j++);
                teacher.setFirst_dept(firstDept.getStringCellValue());
                XSSFCell secondDept = row.getCell(j++);
                teacher.setSecond_dept(secondDept.getStringCellValue());
                XSSFCell name = row.getCell(j++);
                teacher.setName(name.getStringCellValue());
                XSSFCell jobNum = row.getCell(j++);
                teacher.setJob_number(getString(jobNum));

                //初始密码：工号后六位
                String jobNum1 = getString(jobNum);
                String psw = jobNum1.substring(jobNum1.length()-6);
                teacher.setEncrypt_password(SecurityUtil.encryptPassword(psw));
                //System.out.println(teacher);
            }
        } catch (Exception e) {
            throw new RuntimeException("excel解析失败");
        }
    }
    public void parseHr(XSSFSheet sheet) throws RuntimeException{
        try {
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            for(int i = firstRowNum+1; i <= lastRowNum; i++){
                Hr hr = new Hr();
                XSSFRow row = sheet.getRow(i);
                int j = row.getFirstCellNum();

                XSSFCell firstDept = row.getCell(j++);
                hr.setFirst_dept(firstDept.getStringCellValue());
                XSSFCell secondDept = row.getCell(j++);
                hr.setSecond_dept(secondDept.getStringCellValue());
                XSSFCell name = row.getCell(j++);
                hr.setName(name.getStringCellValue());
                XSSFCell jobNum = row.getCell(j++);
                hr.setJob_number(getString(jobNum));

                String jobNum1 = getString(jobNum);
                String psw = jobNum1.substring(jobNum1.length()-6);
                hr.setEncrypt_password(SecurityUtil.encryptPassword(psw));
            }
        } catch (Exception e) {
            throw new RuntimeException("excel解析失败");
        }
    }

    /**
     * 不能确定excel中的工号是string还是number类型，进行转换一下
     * @param xssfCell
     * @return
     */
    public String getString(XSSFCell xssfCell){
        if(xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC){
            return String.valueOf(xssfCell.getNumericCellValue()).split("\\.")[0];
        }else{
            return xssfCell.getStringCellValue();
        }
    }
}
