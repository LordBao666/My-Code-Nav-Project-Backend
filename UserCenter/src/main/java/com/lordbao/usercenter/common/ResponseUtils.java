package com.lordbao.usercenter.common;


/**
 * @Author Lord_Bao
 * @Date 2024/10/12 16:49
 * @Version 1.0
 */
public class ResponseUtils {

    public static <T> BaseResponse<T> SUCCESS(T data, String description) {
        return new BaseResponse<>(CommonResponse.SUCCESS.getCode(), data, CommonResponse.SUCCESS.getMessage(), description);
    }

    public static <T> BaseResponse<T> SUCCESS(T data) {
        return ResponseUtils.SUCCESS(data, "");
    }

    public static <T> BaseResponse<T> ERROR(Integer code, T data, String message, String description) {
        return new BaseResponse<>(code, data, message, description);
    }

    public static <T> BaseResponse<T> ERROR(Integer code, T data, String message) {
        return ResponseUtils.ERROR(code, data, message, "");
    }

}
