package com.dev.usersweb.security.handler;

import com.dev.usersweb.facade.UserFacade;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final String USERNAME = "username";
    private String forwardUrl;

    @Resource
    private RedirectStrategy defaultRedirectStrategy;

    @Resource
    private UserFacade userFacade;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME", request.getParameter(USERNAME));
            session.setAttribute(USERNAME, authentication.getName());
            session.setAttribute("authorities", authentication.getAuthorities());
            session.setAttribute("currentUser", userFacade.getUserByUsername(request.getParameter(USERNAME)));
        }
        defaultRedirectStrategy.sendRedirect(request, response, getForwardUrl());
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }
}
