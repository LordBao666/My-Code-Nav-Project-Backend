package com.lordbao.usercenter;

import com.lordbao.usercenter.mapper.UserMapper;
import com.lordbao.usercenter.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class UserCenterApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        User user = new User();

        user.setUsername("jack");
        user.setUserAccount("");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("");
        user.setEmail("");
        user.setUserStatus(0);

        userMapper.insert(user);
    }

    /**测试加密*/
    @Test
    public void testEncryption(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "123456x";
        String encryptedPassword = encoder.encode(password);
        System.out.println("encryptedPassword:"+encryptedPassword);

        Assertions.assertTrue(encoder.matches(password,encryptedPassword));
        Assertions.assertFalse(encoder.matches("123456y",encryptedPassword));
    }
}
