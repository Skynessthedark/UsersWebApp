package com.dev.usersweb.security.handler;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String noAuthUrl;

    @Resource
    private RedirectStrategy defaultRedirectStrategy;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, true);
        }
        defaultRedirectStrategy.sendRedirect(request, response, getNoAuthUrl());
    }

    public void setNoAuthUrl(String noAuthUrl) {
        this.noAuthUrl = noAuthUrl;
    }

    public String getNoAuthUrl() {
        return noAuthUrl;
    }
}
