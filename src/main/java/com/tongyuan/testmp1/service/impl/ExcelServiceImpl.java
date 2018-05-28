package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tongyuan.testmp1.dao.*;
import com.tongyuan.testmp1.entity.*;
import com.tongyuan.testmp1.exception.NumException;
import com.tongyuan.testmp1.exception.PhoneNumException;
import com.tongyuan.testmp1.helper.PwdHelper;
import com.tongyuan.testmp1.service.*;
import com.tongyuan.testmp1.util.SecurityUtil;
import com.tongyuan.testmp1.viewModel.EvaluationView;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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
    public void parse(InputStream inputStream) {
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("xssfWrokBook创建失败");
        }
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
    }

    public void parseStudent(XSSFSheet sheet){
        try {
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = getRealRowNum(sheet,true);

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
                stuinfo.setName(getString(name));
                XSSFCell jobNum = row.getCell(j++);
                //实习生工号为8位
                stuinfo.setJob_number(getNumber(jobNum,8));
                //手机号为11位
                XSSFCell phone = row.getCell(j++);
                stuinfo.setPhone_number(getPhoneNum(phone));
                XSSFCell sex = row.getCell(j++);
                stuinfo.setSex(getString(sex));
                XSSFCell id = row.getCell(j++);
                stuinfo.setId_number(getString(id));
                XSSFCell email = row.getCell(j++);
                stuinfo.setEmail_addr(getString(email));
                XSSFCell education = row.getCell(j++);
                stuinfo.setEducation(getString(education));
                XSSFCell college = row.getCell(j++);
                stuinfo.setGraduate_college(getString(college));
                XSSFCell major = row.getCell(j++);
                stuinfo.setMajor(getString(major));
                XSSFCell job = row.getCell(j++);
                stuinfo.setJob(getString(job));
                XSSFCell jobDir = row.getCell(j++);
                stuinfo.setJob_direction(getString(jobDir));
                XSSFCell place = row.getCell(j++);
                stuinfo.setPlace(getString(place));
                XSSFCell hireTime = row.getCell(j++);
                stuinfo.setHire_time(readDate(hireTime));
                XSSFCell firstDept = row.getCell(j++);
                stuinfo.setFirst_dept(getString(firstDept));
                XSSFCell secondDept = row.getCell(j++);
                stuinfo.setSecond_dept(getString(secondDept));
                XSSFCell hrName = row.getCell(j++);
                stuinfo.setHr_name(getString(hrName));
                XSSFCell hrJobNum = row.getCell(j++);
                stuinfo.setHr_job_number(getString(hrJobNum));
                XSSFCell teacherName = row.getCell(j++);
                stuinfo.setTeacher_name(getString(teacherName));
                XSSFCell teacherJobNum = row.getCell(j++);
                stuinfo.setTeacher_job_number(getString(teacherJobNum));
                XSSFCell quitTime = row.getCell(j++);
                stuinfo.setQuit_time(readDate(quitTime));
                XSSFCell quitReason = row.getCell(j++);
                stuinfo.setQuit_reason(getString(quitReason));

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
        } catch (NumException e){
            throw new NumException();
        }
        catch (PhoneNumException e){
            throw new PhoneNumException();
        } catch (Exception e) {
            throw new RuntimeException("excel解析student失败");
        }
    }
    public void parseTeacher(XSSFSheet sheet){
        try {
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = getRealRowNum(sheet,false);

            List<Teacher> teacherList = new ArrayList<>();
            for(int i = firstRowNum+1; i <= lastRowNum; i++){
                Teacher teacher = new Teacher();
                XSSFRow row = sheet.getRow(i);
                int j = row.getFirstCellNum();

                XSSFCell firstDept = row.getCell(j++);
                teacher.setFirst_dept(getString(firstDept));
                XSSFCell secondDept = row.getCell(j++);
                teacher.setSecond_dept(getString(secondDept));
                XSSFCell name = row.getCell(j++);
                teacher.setName(getString(name));
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
            throw new RuntimeException("excel解析teacher失败");
        }
    }
    public void parseHr(XSSFSheet sheet){
        try {
            int firstRowNum = sheet.getFirstRowNum();
            //获取sheet中最后一行行号
            int lastRowNum = getRealRowNum(sheet,false);

            List<Hr> hrList = new ArrayList<>();
            for(int i = firstRowNum+1; i <= lastRowNum; i++){
                Hr hr = new Hr();
                XSSFRow row = sheet.getRow(i);
                int j = row.getFirstCellNum();

                XSSFCell firstDept = row.getCell(j++);
                hr.setFirst_dept(getString(firstDept));
                XSSFCell secondDept = row.getCell(j++);
                hr.setSecond_dept(getString(secondDept));
                XSSFCell name = row.getCell(j++);
                hr.setName(getString(name));
                XSSFCell jobNum = row.getCell(j++);
                hr.setJob_number(getString(jobNum));

                String jobNum1 = getString(jobNum);
                String psw = PwdHelper.getPwd(jobNum1);
                hr.setEncrypt_password(SecurityUtil.encryptPassword(psw));

                hrList.add(hr);
            }
            hrService.insertBatch(hrList);
        } catch (Exception e) {
            throw new RuntimeException("excel解析hr失败");
        }
    }

    @Override
    public void createExcelStream(ServletOutputStream outputStream){
        try{
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet planSheet = workbook.createSheet("培养计划表");
            createPlanExcel(planSheet);
            XSSFSheet summarySheet = workbook.createSheet("学生总结表");
            createSummaryExcel(summarySheet);
            XSSFSheet messageSheet = workbook.createSheet("学生留言表");
            createMessageExcel(messageSheet);
            XSSFSheet evaluationSheet = workbook.createSheet("导师评价学生表");
            createEvaluationExcel(evaluationSheet);
            workbook.write(outputStream);
        }catch(Exception e){
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
            dataRow.createCell(j++).setCellValue(evaluationView.getRank()==null?"":String.valueOf(evaluationView.getRank()));
            dataRow.createCell(j++).setCellValue(evaluationView.getEvaluation());
        }
    }

    /*
    获取日期值
     */
    Date readDate(XSSFCell cell){
        if(null==cell || null == cell.getDateCellValue()){
            return null;
        }else{
            return new Date(cell.getDateCellValue().getTime());
        }
    }

    /*
    获取真实行数
     */
    int getRealRowNum(XSSFSheet sheet,boolean isStudent){
        int lastNum = sheet.getLastRowNum();
        int realNum = 1;
        while(realNum<=lastNum+1){
            Row row = sheet.getRow(realNum);
            if(null == row){
                return realNum-1;
            }
            XSSFCell cell;
            if(isStudent){
                cell = (XSSFCell)row.getCell(1);
            }else{
                cell = (XSSFCell)row.getCell(3);
            }
            if("".equals(getString(cell))){
                break;
            }
            realNum++;
        }
        return realNum-1;
    }

    /*
    获取excel中 n位的数字(当n大于6时）
     */
    String getNumber(XSSFCell cell,Integer n){
        try{
        if(null==cell){
            return "";
        }
        if(cell.CELL_TYPE_NUMERIC == cell.getCellType()){
            String part0 = String.valueOf(cell.getNumericCellValue()).split("\\.")[0];
            String part1 = String.valueOf(cell.getNumericCellValue()).split("\\.")[1];
            return part0+part1.substring(0,n-1);
        }else if(cell.CELL_TYPE_STRING == cell.getCellType()){
            return cell.getStringCellValue();
        }else{
            return "";
        }}
        catch(Exception e){
            throw new NumException();
        }
    }

    /*
    获取手机号 (11位）
     */
    String getPhoneNum(XSSFCell cell){
        if(null==cell){
            return "";
        }
        if(cell.CELL_TYPE_NUMERIC == cell.getCellType()){
            String num = String.valueOf(cell.getNumericCellValue()).split("E")[1];
            if(!num.equals("11")){
                throw new PhoneNumException();
            }
        }
        return getNumber(cell,11);
    }

    /**
     * 不能确定excel中的工号是string还是number类型，进行转换一下
     * @param xssfCell
     * @return
     */
    public String getString(XSSFCell xssfCell){
        if(xssfCell==null){
            return "";
        }
        if(xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC){
            return String.valueOf(xssfCell.getNumericCellValue()).split("\\.")[0];
        }else{
            return xssfCell.getStringCellValue();
        }
    }

}
