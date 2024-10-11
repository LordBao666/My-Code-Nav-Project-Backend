package com.lordbao.usercenter.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @Author Lord_Bao
 * @Date 2024/10/11 16:28
 * @Version 1.0
 */
@Configuration
public class DataBaseConfig {

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                // 在插入时填充 createTime
                this.setFieldValByName("createTime", new Date(), metaObject);
                // 在插入时填充 updateTime
                this.setFieldValByName("updateTime", new Date(), metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                // 在更新时填充 updateTime
                this.setFieldValByName("updateTime", new Date(), metaObject);
            }
        };
    }
}
