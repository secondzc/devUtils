package com.tongyuan.testmp1.helper;

import com.tongyuan.testmp1.entity.User;

/**
 * Created by zhangcy on 2018/4/12
 * student的token（studentInfo信息太多了）
 */
public class Token implements User{
    /*
    与stuinfo的id相同
     */
    private Integer id;
    private String jobNumber;
    private String encrptPwd;

    public Token(Integer id, String jobNumber, String encrptPwd) {
        this.id = id;
        this.jobNumber = jobNumber;
        this.encrptPwd = encrptPwd;
    }

    public Integer getId() {
        return id;
    }

    public String getJobNumber() {
        return jobNumber;
    }


    public String getEncrptPwd() {
        return encrptPwd;
    }

}
