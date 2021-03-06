package com.tongyuan.testmp1.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by zhangcy on 2018/4/6
 */
@Entity
public class Stuinfo implements User{
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    /**
     *工号
     */
    @Column
    private String job_number;
    @Column
    private String phone_number;
    @Column
    private String sex;
    /**
     * 身份证号
     */
    @Column
    private String id_number;
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
    private Date hire_time;
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
    private Date quit_time;
    @Column
    private String quit_reason;
    @Column
    private String encrypt_password;
    @Column
    private Integer rank;
    @Column(length = 4095)
    private String evaluation;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
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

    public Date getHire_time() {
        return hire_time;
    }

    public void setHire_time(Date hire_time) {
        this.hire_time = hire_time;
    }

    public Date getQuit_time() {
        return quit_time;
    }

    public void setQuit_time(Date quit_time) {
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

    public String getEncrypt_password() {
        return encrypt_password;
    }

    public void setEncrypt_password(String encrypt_password) {
        this.encrypt_password = encrypt_password;
    }

    @Override
    public String toString() {
        return "Stuinfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job_number='" + job_number + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", sex='" + sex + '\'' +
                ", id_number='" + id_number + '\'' +
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
