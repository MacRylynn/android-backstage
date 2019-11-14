package com.android.common.exception;

/**
 * @ClassName BusinessException
 * @Description 全局统一异常构造
 * @Author: lijiao73
 * @Date: 2019/11/14 14:03
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }
    public BusinessException(Throwable cause) {
        super(cause);
    }

}
