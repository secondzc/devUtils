package com.tongyuan.testmp1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by zhangcy on 2018/4/6
 */
@Entity
public class Hr {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String first_dept;
    @Column
    private String second_dept;
    @Column
    private String job_number;
    @Column
    private String name;
    @Column
    private String encrypt_password;

    public String getEncrypt_password() {
        return encrypt_password;
    }

    public void setEncrypt_password(String encrypt_password) {
        this.encrypt_password = encrypt_password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hr{" +
                "id=" + id +
                ", first_dept='" + first_dept + '\'' +
                ", second_dept='" + second_dept + '\'' +
                ", job_number='" + job_number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
