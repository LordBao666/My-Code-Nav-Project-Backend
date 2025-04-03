package com.lordbao.usercenter.common;


import lombok.Getter;

/**
 * @Author Lord_Bao
 * @Date 2025/4/2 12:42
 * @Version 1.0
 * <p>
 * <p>
 */
@Getter
public enum ResponseCode {
    /**
     * 通用失败响应码0
     */
    FAILURE(0, "失败","操作失败"),
    /**
     * 通用成功响应码200
     */
    SUCCESS(200, "成功","操作成功"),


    PARAM_ERROR(40000, "参数错误","请求参数错误"),
    NULL_ERROR(40001, "参数错误","请求参数为空"),
    NOT_LOGIN(40100, "权限错误","用户未登录"),
    NOT_AUTH(40101, "权限错误","用户权限不足"),
    PLANET_CODE_TAKEN(40200, "星球编码已经占用","星球编码已经占用"),
    USER_ACCOUNT_TAKEN(40201, "账户已经占用","账户已经占用"),


    SYSTEM_ERROR(50000,"服务器内部异常","服务器内部异常");
    /**
     * 响应码
     */
    private final Integer code;
    /**
     * 响应码概要信息
     */
    private final String message;
    /**
     * 响应码详细信息
     */
    private final String description;


    ResponseCode(Integer code, String message,String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }


}
