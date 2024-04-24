package com.dev.usersweb.facade.impl;

import com.dev.usersweb.data.UserData;
import com.dev.usersweb.facade.UserFacade;
import com.dev.usersweb.mapper.UserMapper;
import com.dev.usersweb.model.UserModel;
import com.dev.usersweb.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Override
    public UserData getUserByUsername(String username){
        try {
            UserModel userModel = (UserModel) userService.findByUsername(username);
            return userMapper.mapUserModel2Data(userModel);
        }catch (Exception ex){
            return null;
        }
    }
}
