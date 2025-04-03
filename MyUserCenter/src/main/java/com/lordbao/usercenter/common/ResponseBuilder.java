package com.lordbao.usercenter.common;


import static com.lordbao.usercenter.common.ResponseCode.SUCCESS;

/**
 * 使用过程
 * 1 调用静态方法 failxxx 或 succeedxxx 创建ResponseBuilder对象,
 * 2 按需对返回ResponseBuilder的对象 调用相应的方法(比如message()).如果必要,此步可省略
 * 3 调用build方法,返回相应的CommonResponse
 */
public class ResponseBuilder<T> {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应概要信息
     */
    private String message;
    /**
     * 响应详细信息
     */
    private String description;

    /**
     * 返回数据
     */
    private T data;

    public ResponseBuilder(Integer code, String message, String description, T data) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.data = data;
    }

    // ------------ 成功响应 ------------
    public static <T> ResponseBuilder<T> succeedWithData(T data) {
        return new ResponseBuilder<>(
                SUCCESS.getCode(),
                SUCCESS.getMessage(),
                SUCCESS.getDescription(),
                data);
    }


    public static ResponseBuilder<Void> succeedWithoutData() {
        return new ResponseBuilder<>(
                SUCCESS.getCode(),
                SUCCESS.getMessage(),
                SUCCESS.getDescription(),
                null);
    }


    // ------------ 失败响应 ------------
    public static <T> ResponseBuilder<T> failWithData(Integer code, String message, String description, T data) {
        return new ResponseBuilder<>(
                code,
                message,
                description,
                data);
    }

    public static ResponseBuilder<Void> failWithoutData(Integer code, String message, String description) {
        return new ResponseBuilder<>(
                code,
                message,
                description,
                null);
    }


    public static <T> ResponseBuilder<T> failWithData(ResponseCode responseCode, String customDescription, T data) {
        return new ResponseBuilder<>(
                responseCode.getCode(),
                responseCode.getMessage(),
                customDescription == null ? responseCode.getDescription() : customDescription,
                data);
    }


    // 简化方法（使用默认描述）
    public static ResponseBuilder<Void> failWithOutData(ResponseCode responseCode, String customDescription) {
        return new ResponseBuilder<>(
                responseCode.getCode(),
                responseCode.getMessage(),
                customDescription == null ? responseCode.getDescription() : customDescription,
                null);
    }


    // 链式方法（支持动态覆盖）
    public ResponseBuilder<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public ResponseBuilder<T> description(String description) {
        this.description = description;
        return this;
    }

    public ResponseBuilder<T> message(String message) {
        this.message = message;
        return this;
    }


    public CommonResponse<T> build() {
        if (code == null || message == null) {
            throw new IllegalStateException("code和message属性是不能为空");
        }
        return new CommonResponse<>(
                code,
                message,
                description,
                data
        );
    }


}
