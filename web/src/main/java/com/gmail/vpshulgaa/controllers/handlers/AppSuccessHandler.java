package com.gmail.vpshulgaa.controllers.handlers;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AppSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LogManager.getLogger(AppSuccessHandler.class);
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    private void handle(HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse,
                        Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (httpServletResponse.isCommitted()) {
            logger.debug("Response has already been committed", targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isAdmin = false;
        boolean isSecurityUser = false;
        boolean isSaleUser = false;
        boolean isCustomerUser = false;
        boolean isManagerUser = false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "ADMIN_PERMISSION":
                    isAdmin = true;
                case "SECURITY_USER_PERMISSION":
                    isSecurityUser = true;
                case "SALE_USER_PERMISSION":
                    isSaleUser = true;
                case "CUSTOMER_USER_PERMISSION":
                    isCustomerUser = true;
                case "MANAGE_BUSINESS_CARD":
                    isManagerUser = true;
            }
        }

        if (isAdmin) {
            return "/web/users";
        } else if (isSecurityUser) {
            return "/web/users";
        } else if (isSaleUser) {
            return "/web/orders";
        } else if (isCustomerUser) {
            return "/web/items";
        } else if (isManagerUser) {
            return "/web/cards";
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
