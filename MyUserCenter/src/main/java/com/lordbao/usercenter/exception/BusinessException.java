package com.lordbao.usercenter.exception;


import com.lordbao.usercenter.common.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @Author Lord_Bao
 * @Date 2025/4/3 11:58
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException{

    /**状态码*/
    private Integer code;
    /**概要信息*/
    private String message;
    /**详细信息*/
    private String description;


    public BusinessException(Integer code, String message,String description) {
        super(message);
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.description = responseCode.getDescription();
    }


    @Serial
    private static final long serialVersionUID = -1110662304332739812L;
}
