package com.tongyuan.testmp1.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by zhangcy on 2018/3/23
 */
public class OrderDetail implements Serializable{
    private Long id;
    private Long user_id;
    private Long goods_id;
    private Timestamp date;
    private Double money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
