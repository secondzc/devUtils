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
     *工号
     */
    @Column
    private String job_umber;
    @Column
    private Integer month;
    @Column
    private Integer week;
    /**
     * 培养目标
     */
    @Column
    private String target;
    /**
     * 时间
     */
    @Column
    private String period;
    /**
     * 知识点及掌握程度
     */
    @Column
    private String knowledge;
    /**
     * 学习材料
     */
    @Column
    private String material;
    /**
     * 输出及其考核方式
     */
    @Column
    private String inspect;

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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getInspect() {
        return inspect;
    }

    public void setInspect(String inspect) {
        this.inspect = inspect;
    }
}
