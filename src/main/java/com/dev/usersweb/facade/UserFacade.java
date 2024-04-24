package com.dev.usersweb.facade;

import com.dev.usersweb.data.UserData;

public interface UserFacade {
    UserData getUserByUsername(String username);
}
