package com.company.taskmanager.services.auth;

import com.company.taskmanager.models.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
                || !(authentication.getPrincipal() instanceof User)) {
            throw new RuntimeException(
                    "User not authenticated or invalid authentication");
        }
        return (User) authentication.getPrincipal();
    }
}
