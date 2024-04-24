package com.dev.usersweb.dao;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDao {
    UserDetails findByUsername(String username);
}
