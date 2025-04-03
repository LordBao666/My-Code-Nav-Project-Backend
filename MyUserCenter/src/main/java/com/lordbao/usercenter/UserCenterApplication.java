package com.lordbao.usercenter;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Lord_Bao
 * @Date 2025/4/2 10:26
 * @Version 1.0
 */


@SpringBootApplication
@MapperScan("com.lordbao.usercenter.mapper")
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class,args).start();

    }
}
