package com.lordbao.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lordbao.usercenter.mapper.UserMapper;
import com.lordbao.usercenter.model.User;
import com.lordbao.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
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
}




