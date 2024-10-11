package com.lordbao.usercenter.model.dto;


import lombok.Data;

/**
 * @Author Lord_Bao
 * @Date 2024/10/11 13:39
 * @Version 1.0
 *
 * 封装前端请求的用户对象
 */
@Data
public class RequestUser {

    private String userAccount;
    private String password;
    private String checkPassword;
}
