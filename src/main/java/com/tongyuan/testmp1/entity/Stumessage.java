package com.tongyuan.testmp1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by zhangcy on 2018/4/6
 */
@Entity
public class Stumessage {
    @Id
    @GeneratedValue
    private Integer id;
    /**
     *工号
     */
    @Column
    private String job_umber;
    @Column
    private String type;
    /**
     * 勇敢星有话说
     */
    @Column(length = 4095)
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJob_umber() {
        return job_umber;
    }

    public void setJob_umber(String job_umber) {
        this.job_umber = job_umber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
