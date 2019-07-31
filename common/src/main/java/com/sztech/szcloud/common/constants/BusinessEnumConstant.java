package com.sztech.szcloud.common.constants;

import lombok.Getter;

public interface BusinessEnumConstant {

    @Getter
    enum ResultEnum {

        SUCCESS("200","成功"),
        ERROR("201","操作失败"),
        USER_PSW_ERROR_MSG("300","用户名或密码错误");

        private String code;
        private String message;

        ResultEnum(String code, String message){
            this.code = code;
            this.message = message;
        }

    }
}
