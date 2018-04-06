package com.tongyuan.testmp1.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by zhangcy on 2018/4/6
 */
@Entity
public class Trainee {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    /**
     *工号
     */
    @Column
    private String jobNumber;
    @Column
    private String phoneNumber;
    @Column
    private String sex;
    /**
     * 身份证号
     */
    @Column
    private String idNumber;
    @Column
    private String emailAddr;
    /**
     *学历
     */
    @Column
    private String education;
    /**
     * 毕业学校
     */
    @Column
    private String graduateCollege;
    @Column
    private String major;
    /**
     *签约岗位
     */
    @Column
    private String job;
    /**
     *岗位方向
     */
    @Column
    private String jobDirection;
    /**
     *入职时间
     */
    @Column
    private Timestamp hireTime;
    @Column
    private String firstDept;
    @Column
    private String secondDept;
    @Column
    private String hrName;
    @Column
    private String hrJobNumber;
    @Column
    private String teacherName;
    @Column
    private String teacherJobNumber;
    @Column
    private Timestamp quitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduateCollege() {
        return graduateCollege;
    }

    public void setGraduateCollege(String graduateCollege) {
        this.graduateCollege = graduateCollege;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobDirection() {
        return jobDirection;
    }

    public void setJobDirection(String jobDirection) {
        this.jobDirection = jobDirection;
    }

    public Timestamp getHireTime() {
        return hireTime;
    }

    public void setHireTime(Timestamp hireTime) {
        this.hireTime = hireTime;
    }

    public String getFirstDept() {
        return firstDept;
    }

    public void setFirstDept(String firstDept) {
        this.firstDept = firstDept;
    }

    public String getSecondDept() {
        return secondDept;
    }

    public void setSecondDept(String secondDept) {
        this.secondDept = secondDept;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getHrJobNumber() {
        return hrJobNumber;
    }

    public void setHrJobNumber(String hrJobNumber) {
        this.hrJobNumber = hrJobNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherJobNumber() {
        return teacherJobNumber;
    }

    public void setTeacherJobNumber(String teacherJobNumber) {
        this.teacherJobNumber = teacherJobNumber;
    }

    public Timestamp getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Timestamp quitTime) {
        this.quitTime = quitTime;
    }
}
