package com.tongyuan.testmp1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by zhangcy on 2018/4/6
 */
@Entity
public class Stuplan {
    @Id
    @GeneratedValue
    private Integer id;
    /**
     *学生信息id
     */
    @Column
    private Integer stuid;
    @Column
    private Integer month;
    /**
     * 培养目标
     */
    @Column
    private String target;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
