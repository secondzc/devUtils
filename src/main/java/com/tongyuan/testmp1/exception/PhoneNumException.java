package com.tongyuan.testmp1.exception;

/**
 * Created by zhangcy on 2018/5/28
 * 手机号位数不对
 */
public class PhoneNumException extends RuntimeException{
    public PhoneNumException() {
        super("手机号位数不为11位");
    }

    public PhoneNumException(String message) {
        super(message);
    }
}
