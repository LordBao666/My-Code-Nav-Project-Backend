package com.lordbao.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lordbao.usercenter.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface UserService extends IService<User> {

    /**
     * @param userAccount   用户账户
     * @param password      用户密码
     * @param checkPassword 用户二次输入密码
     * @param planetCode 星球编号
     * @return 如果注册成功， 返回用户ID，否则返回-1
     */
    public Long registerUser(String userAccount, String password, String checkPassword, String planetCode);

    /**
     * @param userAccount 用户账户
     * @param password    用户密码
     * @param request 请求
     * @return 登录成功返回脱敏后的账户信息, 登录失败返回null.
     */
    public User loginUser(String userAccount, String password, HttpServletRequest request);

    /**
     *
     * @param username 用户名
     * @return 根据用户名模糊查询得到的User List
     */
    public List<User> searchUsers(String username);

    /**
     *
     * 注销用户 默认返回1 表示注销成功
     */
    public Integer logoutUser(HttpServletRequest request);
}
