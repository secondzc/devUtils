package com.tongyuan.testmp1.dao;

import com.tongyuan.testmp1.entity.Stuinfo;

import java.util.List;

/**
 * Created by zhangcy on 2018/4/8
 */
public interface UnionMapper {
    List<Stuinfo> selectStdudentByHr(Integer hrId);
}
