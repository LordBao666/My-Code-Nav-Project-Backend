package com.lordbao.usercenter.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Lord_Bao
 * @Date 2025/4/2 12:52
 * @Version 1.0
 *
 * 通用响应实体
 *
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommonResponse<T> {
    /**响应码*/
    private Integer code;
    /**响应概要信息*/
    private  String message;
    /**响应详细信息*/
    private String description;

    /**返回数据*/
    private T data;

}
