package com.lordbao.usercenter.controller;


import com.lordbao.usercenter.model.User;
import com.lordbao.usercenter.model.dto.RequestUser;
import com.lordbao.usercenter.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Lord_Bao
 * @Date 2024/10/11 13:36
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Long registerUser(@RequestBody RequestUser requestUser) {
        String userAccount = requestUser.getUserAccount();
        String password = requestUser.getPassword();
        String checkPassword = requestUser.getCheckPassword();

        if (StringUtils.isAnyBlank(userAccount, password, checkPassword)) {
            return null;
        }

        return userService.registerUser(userAccount, password, checkPassword);

    }

    @PostMapping("login")
    public User loginUser(@RequestBody RequestUser requestUser, HttpServletRequest request) {
        String userAccount = requestUser.getUserAccount();
        String password = requestUser.getPassword();


        if (StringUtils.isAnyBlank(userAccount, password)) {
            return null;
        }

        return userService.loginUser(userAccount, password, request);

    }
}
