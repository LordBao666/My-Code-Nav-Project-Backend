package com.lordbao.usercenter.controller;


import com.lordbao.usercenter.common.BaseResponse;
import com.lordbao.usercenter.common.ResponseUtils;
import com.lordbao.usercenter.constant.UserConstant;
import com.lordbao.usercenter.exception.BusinessException;
import com.lordbao.usercenter.model.User;
import com.lordbao.usercenter.model.dto.RequestUser;
import com.lordbao.usercenter.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.lordbao.usercenter.common.CommonResponse.*;

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
    public BaseResponse<Long> registerUser(@RequestBody RequestUser requestUser) {
        String userAccount = requestUser.getUserAccount();
        String password = requestUser.getPassword();
        String checkPassword = requestUser.getCheckPassword();
        String planetCode = requestUser.getPlanetCode();

        if (StringUtils.isAnyBlank(userAccount, password, checkPassword, planetCode)) {
            throw new BusinessException(NULL_ERROR.getCode(), NULL_ERROR.getMessage());
        }

        return ResponseUtils.SUCCESS(userService.registerUser(userAccount, password, checkPassword, planetCode),"注册成功");

    }

    @PostMapping("login")
    public BaseResponse<User> loginUser(@RequestBody RequestUser requestUser, HttpServletRequest request) {
        String userAccount = requestUser.getUserAccount();
        String password = requestUser.getPassword();

        if (StringUtils.isAnyBlank(userAccount, password)) {
            throw new BusinessException(NULL_ERROR.getCode(), NULL_ERROR.getMessage());
        }
        return ResponseUtils.SUCCESS(userService.loginUser(userAccount, password, request));

    }


    @GetMapping("search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        checkLoginStateAndAuthority(request);
        return ResponseUtils.SUCCESS(userService.searchUsers(username));
    }

    @PostMapping("delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody RequestUser user, HttpServletRequest request) {
        checkLoginStateAndAuthority(request);
        Long id = user.getId();
        if (id <= 0) {
            throw new BusinessException(PARAM_ERROR.getCode(), PARAM_ERROR.getMessage(), "id 只能为正数");
        }
        return userService.removeById(id)
                ? ResponseUtils.SUCCESS(true, "用户删除成功")
                : ResponseUtils.ERROR(ERROR.getCode(), false, ERROR.getMessage(), "用户删除失败");
    }

    @PostMapping("logout")
    public BaseResponse<Object> logoutUser(HttpServletRequest request) {
        userService.logoutUser(request);
        return ResponseUtils.SUCCESS(null);
    }

    /**
     *  检测登录状态和用户权限
     */
    private void checkLoginStateAndAuthority(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if(user==null){
            throw new BusinessException(NOT_LOGIN.getCode(), NOT_LOGIN.getMessage());
        }
        if(!Objects.equals(user.getUserRole(), UserConstant.ADMIN_ROLE)){
            throw new BusinessException(NOT_AUTH.getCode(), NOT_AUTH.getMessage());
        }
    }
}
