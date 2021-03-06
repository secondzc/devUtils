package com.tongyuan.testmp1.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by zhangcy on 2018/2/15
 */
@TableName("user")
public class Userr implements Serializable {
    private Long id;
    private String age;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Userr{" +
                "id=" + id +
                ", age='" + age + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}