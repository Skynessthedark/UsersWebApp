package com.dev.usersweb.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails findByUsername(String username);
}
