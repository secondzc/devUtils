package com.tongyuan.testmp1.service;

import com.baomidou.mybatisplus.service.IService;
import com.tongyuan.testmp1.entity.Teacher;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/6
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 根据部门查找导师
     * @param firstDept
     * @param secondDept
     * @return
     */
    List<Teacher> selectByDept(String firstDept,String secondDept);
}
