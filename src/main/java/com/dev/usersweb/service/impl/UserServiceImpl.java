package com.dev.usersweb.service.impl;

import com.dev.usersweb.dao.UserDao;
import com.dev.usersweb.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails findByUsername(String username){
        return userDao.findByUsername(username);
    }
}
