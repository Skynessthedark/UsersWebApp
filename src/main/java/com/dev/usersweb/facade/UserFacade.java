package com.dev.usersweb.facade;

import com.dev.usersweb.data.ResultData;
import com.dev.usersweb.data.UserData;

import java.util.List;

public interface UserFacade {
    UserData getUserByUsername(String username);

    List<UserData> getUsers();

    UserData getUserById(String id);

    UserData getUserForm(String id);

    boolean saveUser(UserData userData);

    ResultData removeUser(String id);
}
