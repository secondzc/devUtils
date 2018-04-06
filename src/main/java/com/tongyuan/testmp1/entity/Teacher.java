package com.tongyuan.testmp1.entity;

/**
 * Created by zhangcy on 2018/4/6
 */
public class Teacher {
    private Integer id;
    private String name;
    private String jobNumber;
    private String firstDept;
    private String secondDept;

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
}
