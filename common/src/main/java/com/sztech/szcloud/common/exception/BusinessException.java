package com.sztech.szcloud.common.exception;

/**
 * @Company 杭州数政科技有限公司
 * @Author 有成
 * @Date 2019-03-11 11:46
 * @Description
 */
public class BusinessException extends RuntimeException {
    /**
     * 异常码
     */
    private String code = "04";

    public BusinessException(String code, String msg){
        super(msg);
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        StringBuffer sb =  new StringBuffer();
        sb.append(getClass().getName()).append("：");
        String message = getLocalizedMessage();
        if(code !=null) sb.append(code).append("\t");
        if(message !=null) sb.append(message);
        return sb.toString();
    }
}
