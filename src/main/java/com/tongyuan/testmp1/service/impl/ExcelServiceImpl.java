package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tongyuan.testmp1.dao.*;
import com.tongyuan.testmp1.entity.*;
import com.tongyuan.testmp1.helper.PwdHelper;
import com.tongyuan.testmp1.service.*;
import com.tongyuan.testmp1.util.SecurityUtil;
import com.tongyuan.testmp1.viewModel.EvaluationView;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcy on 2018/4/6
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private StuinfoService stuinfoService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private HrService hrService;
    @Autowired
    private PlandetailMapper plandetailMapper;
    @Autowired
    private ViewMapper viewMapper;
    @Autowired
    private StuplanMapper stuplanMapper;
    @Autowired
    private StusummaryMapper stusummaryMapper;
    @Autowired
    private StumessageMapper stumessageMapper;

    @Transactional
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

            //先清除老数据
            stuinfoService.delete(null);
            hrService.delete(null);
            teacherService.delete(null);

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

            List<Stuinfo> stuinfoList = new ArrayList<>(100);
            int count =0;
            for(int i = firstRowNum+1; i <= lastRowNum;i++){
                //每100条数据批量插入数据库
                if(count>=100){
                    stuinfoService.insertBatch(stuinfoList);
                    stuinfoList.clear();
                }
                Stuinfo stuinfo= new Stuinfo();
                XSSFRow row = sheet.getRow(i);
                int j = row.getFirstCellNum();

                XSSFCell name = row.getCell(j++);
                stuinfo.setName(name.getStringCellValue());
                XSSFCell jobNum = row.getCell(j++);
                stuinfo.setJob_number(getString(jobNum));
                XSSFCell phone = row.getCell(j++);
                stuinfo.setPhone_number(getString(phone));
                XSSFCell sex = row.getCell(j++);
                stuinfo.setSex(sex.getStringCellValue());
                XSSFCell id = row.getCell(j++);
                stuinfo.setId_number(getString(id));
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
                stuinfo.setHire_time(new Date(hireTime.getDateCellValue().getTime()));
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
                stuinfo.setQuit_time(new Date(quitTime.getDateCellValue().getTime()));
                XSSFCell quitReason = row.getCell(j++);
                stuinfo.setQuit_reason(quitReason.getStringCellValue());

                //设置初始密码:身份证后六位
                String idcard = getString(id);
                String psw = PwdHelper.getPwd(idcard);
                stuinfo.setEncrypt_password(SecurityUtil.encryptPassword(psw));

                count++;
                stuinfoList.add(stuinfo);
            }
            //插入剩下的
            if(stuinfoList.size()>0){
                stuinfoService.insertBatch(stuinfoList);
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

            List<Teacher> teacherList = new ArrayList<>();
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
                String psw = PwdHelper.getPwd(jobNum1);
                teacher.setEncrypt_password(SecurityUtil.encryptPassword(psw));
                //System.out.println(teacher);
                teacherList.add(teacher);
            }
            teacherService.insertBatch(teacherList);
        } catch (Exception e) {
            throw new RuntimeException("excel解析失败");
        }
    }
    public void parseHr(XSSFSheet sheet) throws RuntimeException{
        try {
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = sheet.getLastRowNum();

            List<Hr> hrList = new ArrayList<>();
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
                String psw = PwdHelper.getPwd(jobNum1);
                hr.setEncrypt_password(SecurityUtil.encryptPassword(psw));

                hrList.add(hr);
            }
            hrService.insertBatch(hrList);
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

    @Override
    public void createExcelStream(ServletOutputStream outputStream){
        try{
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet planSheet = workbook.createSheet("plan");
            createPlanExcel(planSheet);
            XSSFSheet summarySheet = workbook.createSheet("summary");
            createSummaryExcel(summarySheet);
            XSSFSheet messageSheet = workbook.createSheet("message");
            createMessageExcel(messageSheet);
            XSSFSheet evaluationSheet = workbook.createSheet("evaluation");
            createEvaluationExcel(evaluationSheet);
            workbook.write(outputStream);
        }catch(IOException e){
            throw new RuntimeException("生成excel错误");
        }

    }

    void createPlanExcel(XSSFSheet sheet){
        Row row = sheet.createRow(0);
        int j=0;
        row.createCell(j++).setCellValue("姓名");
        row.createCell(j++).setCellValue("工号");
        row.createCell(j++).setCellValue("培养目标");
        row.createCell(j++).setCellValue("时间");
        row.createCell(j++).setCellValue("知识点及掌握程度");
        row.createCell(j++).setCellValue("学习材料");
        row.createCell(j++).setCellValue("输出及考核方式");
        List<Plandetail> plandetailList = plandetailMapper.selectList(null);
        for(int i=0;i<plandetailList.size();i++){
            Row dataRow = sheet.createRow(i+1);
            Plandetail plandetail = plandetailList.get(i);
            Integer stuid = plandetail.getStuid();
            StudentView studentView = viewMapper.selectStuViewById(stuid);
            String name = studentView.getName();
            String jobNum = studentView.getJob_number();
            String target = null;
            Integer month = plandetail.getMonth();
            List<Stuplan> stuplanList = stuplanMapper.selectList(new EntityWrapper<Stuplan>().eq("month",month).eq("stuid",stuid));
            if(stuplanList!=null && 1==stuplanList.size()){
                target = stuplanList.get(0).getTarget();
            }
            String period = plandetail.getPeriod();
            String knowledge = plandetail.getKnowledge();
            String material = plandetail.getMaterial();
            String inspect = plandetail.getInspect();

            j = 0;
            dataRow.createCell(j++).setCellValue(name);
            dataRow.createCell(j++).setCellValue(jobNum);
            dataRow.createCell(j++).setCellValue(target);
            dataRow.createCell(j++).setCellValue(period);
            dataRow.createCell(j++).setCellValue(knowledge);
            dataRow.createCell(j++).setCellValue(material);
            dataRow.createCell(j++).setCellValue(inspect);
        }
    }
    void createSummaryExcel(XSSFSheet sheet){
        Row row = sheet.createRow(0);
        int j=0;
        row.createCell(j++).setCellValue("姓名");
        row.createCell(j++).setCellValue("工号");
        row.createCell(j++).setCellValue("月度总结");
        row.createCell(j++).setCellValue("问题和困难");
        row.createCell(j++).setCellValue("下个月计划");

        List<Stusummary> stusummaryList = stusummaryMapper.selectList(null);
        for(int i=0;i<stusummaryList.size();i++){
            Row dataRow = sheet.createRow(i+1);
            Stusummary stusummary = stusummaryList.get(i);
            Integer stuid = stusummary.getStuid();
            StudentView studentView = viewMapper.selectStuViewById(stuid);
            String name = studentView.getName();
            String jobNum = studentView.getJob_number();

            j=0;
            dataRow.createCell(j++).setCellValue(name);
            dataRow.createCell(j++).setCellValue(jobNum);
            dataRow.createCell(j++).setCellValue(stusummary.getSummary());
            dataRow.createCell(j++).setCellValue(stusummary.getQuestion());
            dataRow.createCell(j++).setCellValue(stusummary.getPlan());
        }

    }
    void createMessageExcel(XSSFSheet sheet){
        Row row = sheet.createRow(0);
        int j=0;
        row.createCell(j++).setCellValue("姓名");
        row.createCell(j++).setCellValue("工号");
        row.createCell(j++).setCellValue("留言类型");
        row.createCell(j++).setCellValue("留言内容");

        List<Stumessage> stumessageList = stumessageMapper.selectList(null);
        for(int i=0;i<stumessageList.size();i++){
            Row dataRow = sheet.createRow(i+1);
            Stumessage stumessage = stumessageList.get(i);
            Integer stuid = stumessage.getStuid();
            StudentView studentView = viewMapper.selectStuViewById(stuid);
            String name = studentView.getName();
            String jobNum = studentView.getJob_number();

            j=0;
            dataRow.createCell(j++).setCellValue(name);
            dataRow.createCell(j++).setCellValue(jobNum);
            dataRow.createCell(j++).setCellValue(stumessage.getType());
            dataRow.createCell(j++).setCellValue(stumessage.getMessage());
        }
    }
    void createEvaluationExcel(XSSFSheet sheet){
        Row row = sheet.createRow(0);
        int j=0;
        row.createCell(j++).setCellValue("姓名");
        row.createCell(j++).setCellValue("工号");
        row.createCell(j++).setCellValue("评级");
        row.createCell(j++).setCellValue("评价内容");

        List<EvaluationView> evaluationViewList = viewMapper.selectAllEvaluations();
        for(int i=0;i<evaluationViewList.size();i++){
            EvaluationView evaluationView = evaluationViewList.get(i);
            Row dataRow =  sheet.createRow(i+1);
            j=0;
            dataRow.createCell(j++).setCellValue(evaluationView.getName());
            dataRow.createCell(j++).setCellValue(evaluationView.getJob_number());
            dataRow.createCell(j++).setCellValue(evaluationView.getRank());
            dataRow.createCell(j++).setCellValue(evaluationView.getEvaluation());
        }
    }
}
