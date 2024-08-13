package com.company.taskmanager.services.auth;

import com.company.taskmanager.models.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling authentication-related operations.
 * <p>
 * This service provides method to interact with the
 * currently authenticated user.
 * </p>
 */
@Service
public class AuthService {

    /**
     * Retrieves the currently authenticated user.
     * <p>
     * This method checks the current {@link Authentication}
     * from the {@link SecurityContextHolder}.
     * It ensures that the authentication is valid and the
     * principal is of type {@link User}.
     * If the authentication is invalid or the principal is not
     * a {@link User}, an exception is thrown.
     * </p>
     *
     * @return the currently authenticated {@link User}.
     * @throws RuntimeException if the user is not authenticated or
     * if the authentication principal is not a {@link User}.
     */
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
