package com.dev.usersweb.service.impl;

import com.dev.usersweb.dao.UserDao;
import com.dev.usersweb.model.UserModel;
import com.dev.usersweb.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails findByUsername(String username){
        return userDao.findByUsername(username);
    }

    @Override
    public List<UserModel> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public UserModel findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public boolean save(UserModel userModel) {
        return userDao.save(userModel);
    }
}
