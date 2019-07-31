package com.sztech.szcloud.common.exception;

/**
 * Created by 子华 on 2017/8/14.
 */
public class HttpException extends BusinessException {

    public HttpException(String code, String msg) {
        super(code, msg);
    }
}
