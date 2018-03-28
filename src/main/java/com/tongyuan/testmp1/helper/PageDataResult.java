package com.tongyuan.testmp1.helper;

import java.util.List;

/**
 * Created by zhangcy on 2018/3/28
 */
public class PageDataResult<T> {
    private Long totals;
    private List<T> list;

    public Long getTotals() {
        return totals;
    }

    public void setTotals(Long totals) {
        this.totals = totals;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
