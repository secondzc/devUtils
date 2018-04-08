package com.tongyuan.testmp1.service;

import com.baomidou.mybatisplus.service.IService;
import com.tongyuan.testmp1.entity.Stuinfo;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/6
 */
public interface StuinfoService extends IService<Stuinfo> {
    /**
     * 根据导师工号查找实习生
     * @param jobNum
     * @return
     */
    List<Stuinfo> selectByTeacherNum(String jobNum);

    /**
     * 根据部门查找实习生
     * @param firstDept
     * @param secondDept
     * @return
     */
    List<Stuinfo> selectByDept(String firstDept,String secondDept);
}
