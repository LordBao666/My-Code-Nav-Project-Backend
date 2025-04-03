package com.lordbao.usercenter.common;

/**
 * 响应实体工具类
 * 方法分为 succeed 和  fail
 * 提供了不同的重载方法, 以便于使用
 */
@Deprecated
public class ResponseUtils {


    //buildResponse是为了让其他工具方法(fail,succeed)返回CommonResponse时更加简洁
    private static <T> CommonResponse<T> buildResponse(Integer code, String message,String description,T data){
        return new CommonResponse<>(code,message,description,data);
    }


    public static  <T>  CommonResponse <T> succeedWithData(String description,T data){
        ResponseCode code = ResponseCode.SUCCESS;
        return buildResponse(code.getCode(),code.getMessage(),description,data);
    }

    public static   CommonResponse <Void> succeedWithoutData(String description){
        ResponseCode code = ResponseCode.SUCCESS;
        return buildResponse(code.getCode(),code.getMessage(),description,null);
    }





    public static  <T>  CommonResponse <T> failWithData(Integer code, String message,String description,T data){
        return buildResponse(code,message,description,data);
    }
    public static    CommonResponse <Void> failWithoutData(Integer code, String message,String description){
        return buildResponse(code,message,description,null);
    }




    public static  <T>  CommonResponse <T> failWithData(ResponseCode code,String description,T data){
        return buildResponse(code.getCode(),code.getMessage(),description,data);
    }

    public static    CommonResponse <Void> failWithoutData(ResponseCode code,String description){
        return buildResponse(code.getCode(),code.getMessage(),description,null);
    }



}