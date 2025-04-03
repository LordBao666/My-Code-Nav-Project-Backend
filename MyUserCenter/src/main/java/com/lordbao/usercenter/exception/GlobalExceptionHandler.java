package com.lordbao.usercenter.exception;


import com.lordbao.usercenter.common.CommonResponse;
import com.lordbao.usercenter.common.ResponseBuilder;
import com.lordbao.usercenter.common.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Lord_Bao
 * @Date 2025/4/2 10:24
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public CommonResponse<Void> runtimeExceptionHandler(RuntimeException e){
        log.error("发生系统内部异常",e);
        return ResponseBuilder.failWithOutData(ResponseCode.SYSTEM_ERROR,null).build();
    }

    @ExceptionHandler(BusinessException.class)
    public CommonResponse<Void> businessExceptionHandler(BusinessException e){
        log.error("发生业务异常,code:{},message:{},description:{}",e.getCode(),e.getMessage(),e.getDescription());
        return ResponseBuilder.failWithoutData(e.getCode(),e.getMessage(),e.getDescription()).build();
    }

}
