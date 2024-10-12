package com.lordbao.usercenter.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @Author Lord_Bao
 * @Date 2024/10/12 20:54
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2853287678543461828L;
    /**
     * 错误代码
     */
    private Integer code;
    /**
     * 错误概要信息
     */
    private String message;
    /**
     * 自定义描述信息
     */
    private String description;

    public BusinessException(Integer code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public BusinessException(Integer code, String message) {
        this(code, message, "");
    }
}
