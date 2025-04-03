package com.lordbao.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lordbao.usercenter.entity.User;
import com.lordbao.usercenter.service.UserService;
import com.lordbao.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




