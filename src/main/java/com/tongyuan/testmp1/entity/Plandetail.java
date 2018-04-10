package com.tongyuan.testmp1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by zhangcy on 2018/4/10
 */
@Entity
public class Plandetail {
    @Id
    @GeneratedValue
    private Integer id;
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
    @Column
    private Integer month;
    @Column
    private Integer stuid;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
