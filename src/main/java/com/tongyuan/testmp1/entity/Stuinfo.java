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
public class Stuinfo {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    /**
     *工号
     */
    @Column
    private String job_umber;
    @Column
    private String phone_umber;
    @Column
    private String sex;
    /**
     * 身份证号
     */
    @Column
    private String id_umber;
    @Column
    private String email_addr;
    /**
     *学历
     */
    @Column
    private String education;
    /**
     * 毕业学校
     */
    @Column
    private String graduate_college;
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
    private String job_direction;
    /**
     * 工作地
     */
    @Column
    private String place;
    /**
     *入职时间
     */
    @Column
    private Timestamp hire_time;
    @Column
    private String first_dept;
    @Column
    private String second_dept;
    @Column
    private String hr_name;
    @Column
    private String hr_job_number;
    @Column
    private String teacher_name;
    @Column
    private String teacher_job_number;
    @Column
    private Timestamp quit_time;
    @Column
    private String quit_reason;

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

    public String getJob_umber() {
        return job_umber;
    }

    public void setJob_umber(String job_umber) {
        this.job_umber = job_umber;
    }

    public String getPhone_umber() {
        return phone_umber;
    }

    public void setPhone_umber(String phone_umber) {
        this.phone_umber = phone_umber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId_umber() {
        return id_umber;
    }

    public void setId_umber(String id_umber) {
        this.id_umber = id_umber;
    }

    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduate_college() {
        return graduate_college;
    }

    public void setGraduate_college(String graduate_college) {
        this.graduate_college = graduate_college;
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

    public String getJob_direction() {
        return job_direction;
    }

    public void setJob_direction(String job_direction) {
        this.job_direction = job_direction;
    }

    public Timestamp getHire_time() {
        return hire_time;
    }

    public void setHire_time(Timestamp hire_time) {
        this.hire_time = hire_time;
    }

    public String getFirst_dept() {
        return first_dept;
    }

    public void setFirst_dept(String first_dept) {
        this.first_dept = first_dept;
    }

    public String getSecond_dept() {
        return second_dept;
    }

    public void setSecond_dept(String second_dept) {
        this.second_dept = second_dept;
    }

    public String getHr_name() {
        return hr_name;
    }

    public void setHr_name(String hr_name) {
        this.hr_name = hr_name;
    }

    public String getHr_job_number() {
        return hr_job_number;
    }

    public void setHr_job_number(String hr_job_number) {
        this.hr_job_number = hr_job_number;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_job_number() {
        return teacher_job_number;
    }

    public void setTeacher_job_number(String teacher_job_number) {
        this.teacher_job_number = teacher_job_number;
    }

    public Timestamp getQuit_time() {
        return quit_time;
    }

    public void setQuit_time(Timestamp quit_time) {
        this.quit_time = quit_time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getQuit_reason() {
        return quit_reason;
    }

    public void setQuit_reason(String quit_reason) {
        this.quit_reason = quit_reason;
    }

    @Override
    public String toString() {
        return "Stuinfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job_umber='" + job_umber + '\'' +
                ", phone_umber='" + phone_umber + '\'' +
                ", sex='" + sex + '\'' +
                ", id_umber='" + id_umber + '\'' +
                ", email_addr='" + email_addr + '\'' +
                ", education='" + education + '\'' +
                ", graduate_college='" + graduate_college + '\'' +
                ", major='" + major + '\'' +
                ", job='" + job + '\'' +
                ", job_direction='" + job_direction + '\'' +
                ", place='" + place + '\'' +
                ", hire_time=" + hire_time +
                ", first_dept='" + first_dept + '\'' +
                ", second_dept='" + second_dept + '\'' +
                ", hr_name='" + hr_name + '\'' +
                ", hr_job_number='" + hr_job_number + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_job_number='" + teacher_job_number + '\'' +
                ", quit_time=" + quit_time +
                ", quit_reason='" + quit_reason + '\'' +
                '}';
    }
}
