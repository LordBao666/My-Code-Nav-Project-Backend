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
     * @param planetCode    星球编号
     * @return 返回注册成功的用户ID
     */
    public Long registerUser(String userAccount, String password, String checkPassword, String planetCode);

    /**
     * @param userAccount 用户账户
     * @param password    用户密码
     * @param request     请求
     * @return 返回登录成功返回脱敏后的账户信息
     */
    public User loginUser(String userAccount, String password, HttpServletRequest request);

    /**
     * @param username 用户名
     * @return 根据用户名模糊查询得到的User List
     */
    public List<User> searchUsers(String username);

    /**
     * 注销用户
     */
    public void logoutUser(HttpServletRequest request);

    /**
     *
     *根据标签检索用户。此处会返回所有标签均符合的用户。
     * 注意tags不能为空，否则抛出异常。
     */
    public List<User> searchUserByTags(List<String> tags);
}
