package com.tongyuan.testmp1.exception;

/**
 * Created by zhangcy on 2018/5/28
 */
public class NumException extends RuntimeException {
    public NumException() {
        super("位数不符合要求");
    }

    public NumException(String message) {
        super(message);
    }
}
