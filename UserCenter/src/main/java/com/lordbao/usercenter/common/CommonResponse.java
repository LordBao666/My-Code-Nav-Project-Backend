package com.lordbao.usercenter.common;


/**
 * @Author Lord_Bao
 * @Date 2024/10/12 21:04
 * @Version 1.0
 */
public enum CommonResponse {
    /**
     * 一般未知错误的代码为-1
     * 成功的代码为 0
     * 请求参数错误的代码为 40000
     * 请求参数为空的代码为 40001
     * <p>
     * 用户未登录的代码为 40100
     * 用户权限不足的代码为 40101
     * <p>
     * 星球编码已经占用  40200
     * 账户已经占用 40201
     */
    ERROR(-1, "错误"),
    SUCCESS(0, "成功"),
    PARAM_ERROR(40000, "请求参数错误"),
    NULL_ERROR(40001, "请求参数为空"),
    NOT_LOGIN(40100, "用户未登录"),
    NOT_AUTH(40101, "用户权限不足"),
    PLANET_CODE_TAKEN(40200, "星球编码已经占用"),
    USER_ACCOUNT_TAKEN(40201, "账户已经占用");

    /**
     * 返回码
     */
    private final Integer code;
    /**
     * 概要信息
     */
    private final String message;

    CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
