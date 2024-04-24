package com.dev.usersweb.facade.impl;

import com.dev.usersweb.data.ResultData;
import com.dev.usersweb.data.UserData;
import com.dev.usersweb.facade.UserFacade;
import com.dev.usersweb.mapper.UserMapper;
import com.dev.usersweb.model.UserModel;
import com.dev.usersweb.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    @Value("${message.error}")
    private String errorMsg;

    @Value("${message.success}")
    private String successMsg;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Override
    public UserData getUserByUsername(String username){
        try {
            UserModel userModel = (UserModel) userService.findByUsername(username);
            return userMapper.mapModel2Data(userModel);
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public List<UserData> getUsers(){
        List<UserModel> users = userService.getUsers();
        return mapUsers(users);
    }

    @Override
    public UserData getUserById(String id) {
        try {
            UserModel userModel = userService.findById(Long.parseLong(id));
            return userMapper.mapModel2Data(userModel);
        }catch (Exception ignored){
            return null;
        }
    }

    @Override
    public UserData getUserForm(String id){
        UserData userData = new UserData();
        if(StringUtils.hasText(id)){
            userData = getUserById(id);
            if(userData == null){
                return new UserData();
            }
        }
        return userData;
    }

    @Override
    public boolean saveUser(UserData userData) {
        UserModel userModel = getUserModel(userData);
        if(userModel == null){
            return false;
        }
        return userService.save(userModel);
    }

    @Override
    public ResultData removeUser(String id) {
        try {
            if (StringUtils.hasText(id)) {
                UserModel userModel = userService.findById(Long.parseLong(id));
                if (userModel != null) {
                    userModel.setRemoved(true);
                    if (userService.save(userModel)) {
                        return getResult(false, successMsg);
                    }
                }
            }
        } catch (Exception ignored) {}
        return getResult(false, errorMsg);
    }

    private UserModel getUserModel(UserData userData){
        UserModel userModel;
        if(StringUtils.hasText(userData.getId())){
            userModel = userService.findById(Long.parseLong(userData.getId()));
            userMapper.update(userModel, userData);
        }else{
            userModel = userMapper.mapData2Model(userData);
        }
        return userModel;
    }

    private ResultData getResult(boolean status, String message){
        return new ResultData(message, status);
    }

    private List<UserData> mapUsers(List<UserModel> users){
        if(CollectionUtils.isEmpty(users)){
            return Collections.emptyList();
        }
        List<UserData> userDataList = new ArrayList<>();
        for (UserModel user : users) {
            userDataList.add(userMapper.mapModel2Data(user));
        }
        return userDataList;
    }
}
