package com.nijikawa.liteblog.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nijikawa.liteblog.entity.User;
import com.nijikawa.liteblog.mapper.UserMapper;
import com.nijikawa.liteblog.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
