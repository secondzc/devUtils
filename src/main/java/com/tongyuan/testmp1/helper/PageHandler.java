package com.tongyuan.testmp1.helper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by zhangcy on 2018/3/28
 * T表示实体类
 */
public abstract class PageHandler<T> {
    /**
     *
     * @param page 当前页数
     * @param limit  每页记录数
     * @return
     */
    public PageDataResult getResult(int page,int limit){
        PageHelper.startPage(page,limit);
        List<T> records = doQuery();
        PageInfo<T> pageInfo = new PageInfo<>(records);
        PageDataResult pageDataResult = new PageDataResult();
        pageDataResult.setTotals(pageInfo.getTotal());
        pageDataResult.setList(records);
        return pageDataResult;
    }

    /*
    回调方法
     */
    protected abstract List<T> doQuery();
}
