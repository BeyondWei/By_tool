package com.sztech.szcloud.common.dto;

import com.sztech.szcloud.common.constants.BusinessEnumConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName ResultsDto
 * @Description
 * @Author WanLi
 * @Date 2019/4/22 14:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultsDto {
    private String code;
    private String msg;
    private Object data;

    /**
     * @deprecated  请求成功返回对象
     * */
    public static ResultsDto success(){
        return success(null);
    }

    /**
     * @param  data 返回的数据
     * @return 返回成功 包含code,msg,data
     * */
    public static ResultsDto success(Object data){
        String code = BusinessEnumConstant.ResultEnum.SUCCESS.getCode();
        String msg = BusinessEnumConstant.ResultEnum.SUCCESS.getMessage();
        return success(code,msg,data);
    }

    /**
     * @param  code  响应码
     * @param  msg   提示信息
     * @param  data  返回数据
     * @return  返回成功 包含code,msg,data
     * */
    public static ResultsDto success(String code,String msg,Object data){
        return new ResultsDto(code,msg,data);
    }

    /**
     * @param responseEnum 返回响应码对应的枚举类
     * @return  返回失败 包含code,msg,data
     * */
    public static ResultsDto error(BusinessEnumConstant.ResultEnum responseEnum){
        return new ResultsDto(responseEnum.getCode(),responseEnum.getMessage(),null);
    }

    /**
     * @param  code  响应码
     * @param  msg   提示信息
     * @return  返回失败 包含code,msg,data
     * */
    public static ResultsDto error(String code,String msg){
        return new ResultsDto(code,msg,null);
    }
}
