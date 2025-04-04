package com.lordbao.usercenter.service.impl;

import com.lordbao.usercenter.model.User;
import com.lordbao.usercenter.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/10/10 17:30
 * @Version 1.0
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void registerUser() {

        //测试userAccount是否为空
        Long result;
        String userAccount = "";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        String planetCode = "666";
        result = userService.registerUser(userAccount, userPassword, checkPassword, planetCode);
        assertEquals(-1, result);

        //测试userAccount的长度是否符合要求
        userAccount = "yu";
        userPassword = "12345678";
        checkPassword = "12345678";
        planetCode = "666";
        result = userService.registerUser(userAccount, userPassword, checkPassword, planetCode);
        assertEquals(-1, result);

        //测试planetCode的长度是否符合要求
        userAccount = "yupi";
        userPassword = "12345678";
        checkPassword = "12345678";
        planetCode = "666444444444444444444444444444";
        result = userService.registerUser(userAccount, userPassword, checkPassword, planetCode);
        assertEquals(-1, result);

        //测试password的长度是否符合要求
        userAccount = "yupi";
        userPassword = "123456";
        checkPassword = "123456";
        planetCode = "666";
        result = userService.registerUser(userAccount, userPassword, checkPassword, planetCode);
        assertEquals(-1, result);

        //测试账户是否含非法字符
        userAccount = "yu pi";
        userPassword = "12345678";
        checkPassword = "12345678";
        planetCode = "666";
        result = userService.registerUser(userAccount, userPassword, checkPassword, planetCode);
        assertEquals(-1, result);

        //测试密码和二次密码是否一致
        userAccount = "yupi";
        userPassword = "12345678";
        checkPassword = "123456789";
        planetCode = "666";
        result = userService.registerUser(userAccount, userPassword, checkPassword, planetCode);
        assertEquals(-1, result);


        //测试用户是否存在
//        Long id = userService.registerUser("jack", "12345678", "12345678");
//        System.out.println(id);
        //在执行下面代码时，确保jack已经在数据库
        userAccount = "jack";
        userPassword = "12345678";
        checkPassword = "12345678";
        planetCode = "666";
        result = userService.registerUser(userAccount, userPassword, checkPassword, planetCode);
        assertEquals(-1, result);
    }

    @Test
    void searchUsersByTag(){

        List<String> tags = List.of("C++", "java");
        List<User> users = userService.searchUserByTags(tags);
        for (User user : users) {
            System.out.println(user);
        }
    }
}