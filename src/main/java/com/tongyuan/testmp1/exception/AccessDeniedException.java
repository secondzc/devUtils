package com.tongyuan.testmp1.exception;

/**
 * Created by zhangcy on 2018/4/19
 * 权限不足
 */
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException() {
        super("权限不足");
    }
}
