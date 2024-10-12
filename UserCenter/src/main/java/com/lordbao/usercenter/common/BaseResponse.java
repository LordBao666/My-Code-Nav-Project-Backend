package com.lordbao.usercenter.common;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author Lord_Bao
 * @Date 2024/10/12 16:46
 * @Version 1.0
 * <p>
 * 通用返回对象
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * 通用返回码
     */
    private Integer code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 概要描述信息
     */
    private String message;

    /**
     * 详细描述信息
     */
    private String description;

    public BaseResponse(Integer code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(Integer code, T data, String message) {
        this(code, data, message, "");
    }

    @Serial
    private static final long serialVersionUID = 5395725370833227797L;

}
