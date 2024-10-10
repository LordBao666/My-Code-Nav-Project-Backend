package com.lordbao.usercenter.service;

import com.lordbao.usercenter.model.User;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UserService extends IService<User> {

    /**
     *
     * @param userAccount 用户账户
     * @param password 用户密码
     * @param checkPassword 用户二次输入密码
     * @return 如果注册成功， 返回用户ID，否则返回-1
     */
    public Long registerUser(String userAccount, String password,String checkPassword);

}
