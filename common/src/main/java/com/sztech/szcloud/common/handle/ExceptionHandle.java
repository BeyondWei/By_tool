package com.sztech.szcloud.common.handle;

import com.sztech.szcloud.common.constants.BusinessEnumConstant;
import com.sztech.szcloud.common.dto.ResultsDto;
import com.sztech.szcloud.common.exception.BusinessException;
import com.sztech.szcloud.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 捕获业务异常
     *
     * @param be
     * @return ResponseVO
     */
    @ExceptionHandler(BusinessException.class)
    public ResultsDto handleBusinessException(BusinessException be) {
        ResultsDto result = new ResultsDto()
                .setCode(be.getCode())
                .setMsg(be.getMessage());
        logger.error("发生异常: \n {}", be);
        return result;
    }

    @ExceptionHandler(CustomException.class)
    public ResultsDto handleCustomException(CustomException be) {
        ResultsDto result = new ResultsDto()
                .setCode("09")
                .setMsg(be.getBody());
        logger.error("发生异常: \n {}", be);
        return result;
    }

    /**
     * 捕获异常，返回Result对象，并将异常记录到日志中
     *
     * @param e
     * @return ResponseVO
     */
    @ExceptionHandler(Exception.class)
    public ResultsDto handleException(Exception e) {
        ResultsDto result = new ResultsDto()
                .setCode(BusinessEnumConstant.ResultEnum.ERROR.getCode())
                .setMsg("出错了，请联系管理员！");
        logger.error("系统异常: \n {}", e);
        return result;
    }

}
