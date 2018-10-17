package com.gmail.vpshulgaa.service.util;

import com.gmail.vpshulgaa.service.dto.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {

    public static UserPrincipal getPrincipal() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }
}
