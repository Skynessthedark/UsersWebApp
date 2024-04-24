package com.dev.usersweb.service;

import com.dev.usersweb.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails findByUsername(String username);

    List<UserModel> getUsers();

    UserModel findById(Long id);

    boolean save(UserModel userModel);
}
