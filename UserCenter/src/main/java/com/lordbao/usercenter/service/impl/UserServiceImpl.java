package com.lordbao.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lordbao.usercenter.constant.UserConstant;
import com.lordbao.usercenter.mapper.UserMapper;
import com.lordbao.usercenter.model.User;
import com.lordbao.usercenter.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public Long registerUser(String userAccount, String password, String checkPassword) {
        //检测是否为空
        if (StringUtils.isAnyBlank(userAccount, password, checkPassword)) {
            return -1L;
        }

        //检查密码和二次密码是否匹配
        if (!password.equals(checkPassword)) {
            return -1L;
        }

        //检测长度是否符合要求
        if (userAccount.length() < 4 || password.length() < 8) {
            return -1L;
        }

        /* 要求账户只能含字母数字下划线
          ^表示从字符串开始进行匹配, $表示匹配到整个字符串的末尾
          [a-zA-Z0-9_]表示字母数字下划线, +表示前面的字符至少出现1个
        */
        String userPattern = "^[a-zA-Z0-9_]+$";
        if (!userAccount.matches(userPattern)) {
            return -1L;
        }

        //检测userAccount是否已经注册
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserAccount, userAccount);
        if (this.count(wrapper) > 0) {
            return -1L;
        }

        //对密码进行加密
        String encryptedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptedPassword);

        boolean saveSuccess = save(user);
        if(saveSuccess){
            return user.getId();
        }
        return -1L;
    }

    @Override
    public User loginUser(String userAccount, String password, HttpServletRequest request) {
        //检测是否为空
        if (StringUtils.isAnyBlank(userAccount, password)) {
            return null;
        }

        //检测长度是否符合要求
        if (userAccount.length() < 4 || password.length() < 8) {
            return null;
        }

        /* 要求账户只能含字母数字下划线
          ^表示从字符串开始进行匹配, $表示匹配到整个字符串的末尾
          [a-zA-Z0-9_]表示字母数字下划线, +表示前面的字符至少出现1个
        */
        String userPattern = "^[a-zA-Z0-9_]+$";
        if (!userAccount.matches(userPattern)) {
            return null;
        }

        //查询用户是否存在
        LambdaQueryWrapper <User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserAccount,userAccount);
        User user = this.getOne(wrapper);
        if(user==null || !passwordEncoder.matches(password,user.getUserPassword())){
            log.info("Username can  NOT match password!");
            return null;
        }

        //用户脱敏
        User safetyUser = getSafeUser(user);
        HttpSession session = request.getSession();
        //记录用户的登录状态
        session.setAttribute(UserConstant.USER_LOGIN_STATE,safetyUser);
        return safetyUser;
    }

    @Override
    public List<User> searchUsers(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(username),User::getUsername,username);
        List<User> list = this.list(wrapper);
        List<User> safetyUsers = new ArrayList<>();
        for(User user:list){
            safetyUsers.add(getSafeUser(user));
        }
        return safetyUsers;
    }

    /**
     *
     * @param originUser 需要脱敏的用户
     * @return 返回脱敏后的用户
     */
    private User getSafeUser(User originUser){
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setUserRole(originUser.getUserRole());
        return safetyUser;
    }
}




