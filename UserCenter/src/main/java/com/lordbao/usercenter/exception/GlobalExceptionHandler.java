package com.lordbao.usercenter.exception;


import com.lordbao.usercenter.common.BaseResponse;
import com.lordbao.usercenter.common.CommonResponse;
import com.lordbao.usercenter.common.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Lord_Bao
 * @Date 2024/10/12 20:57
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<Object> businessExceptionHandler(BusinessException e) {
        Integer code = e.getCode();
        String message = e.getMessage();
        String description = e.getDescription();
        log.error("BusinessException happens: code :{}, message : {}, description: {}", code, message, description);
        return ResponseUtils.ERROR(code, null, message, description);
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<Object> runtimeExceptionHandler(RuntimeException e) {
        log.error("Runtime Exception happens: {}", e.getMessage());
        return ResponseUtils.ERROR(CommonResponse.ERROR.getCode(), null, CommonResponse.ERROR.getMessage(), "");
    }
}
