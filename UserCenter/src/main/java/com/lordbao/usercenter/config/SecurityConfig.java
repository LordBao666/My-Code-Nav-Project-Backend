package com.lordbao.usercenter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Lord_Bao
 * @Date 2024/10/10 17:24
 * @Version 1.0
 */
@Configuration
public class SecurityConfig {

    /**SpringSecurity 提供的加密组件*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
