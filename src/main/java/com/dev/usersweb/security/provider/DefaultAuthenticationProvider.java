package com.dev.usersweb.security.provider;

import com.dev.usersweb.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

public class DefaultAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserDetails userModel = userService.findByUsername(username);

        if (Objects.nonNull(userModel)
                && userModel.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(
                    userModel.getUsername(), userModel.getPassword(), userModel.getAuthorities());
        }
        throw new UsernameNotFoundException("User not found by given credentials.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
