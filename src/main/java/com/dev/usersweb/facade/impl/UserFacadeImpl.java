package com.dev.usersweb.facade.impl;

import com.dev.usersweb.data.ResultData;
import com.dev.usersweb.data.UserData;
import com.dev.usersweb.enums.UserRole;
import com.dev.usersweb.facade.UserFacade;
import com.dev.usersweb.mapper.UserMapper;
import com.dev.usersweb.model.UserModel;
import com.dev.usersweb.service.UserService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacadeImpl.class);

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
            LOGGER.error("getUserByUsernameExp: ", ex);
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
        }catch (Exception e){
            LOGGER.error("getUserByIdExp: ", e);
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
            LOGGER.error("saveUserExp: User cannot be null.");
            return false;
        }
        if(Boolean.TRUE.equals(userData.isAdmin())){
            userModel.getRoles().add(UserRole.ADMIN);
        }else{
            userModel.getRoles().remove(UserRole.ADMIN);
        }
        return userService.save(userModel);
    }

    @Override
    public ResultData checkIfUserExists(String username) {
        UserModel userModel = (UserModel) userService.findByUsername(username);
        return getResult(userModel != null, successMsg);
    }

    @Override
    public ResultData removeUser(String id) {
        try {
            if (StringUtils.hasText(id)) {
                UserModel userModel = userService.findById(Long.parseLong(id));
                if (userModel != null) {
                    userModel.setRemoved(true);
                    if (userService.save(userModel)) {
                        return getResult(true, successMsg);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error("removeUserExp: ", ex);
        }
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
