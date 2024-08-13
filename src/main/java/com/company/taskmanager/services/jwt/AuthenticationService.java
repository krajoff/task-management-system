package com.company.taskmanager.services.jwt;

import com.company.taskmanager.exceptions.AuthException;
import com.company.taskmanager.requests.SignInRequest;
import com.company.taskmanager.requests.SignUpRequest;
import com.company.taskmanager.models.user.Role;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.responces.JwtAuthenticationResponse;
import com.company.taskmanager.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling user authentication and registration.
 * <p>
 * This service manages the following operations:
 * - User registration by creating a new user and generating a JWT token.
 * - User authentication by validating user credentials and generating a JWT token.
 * </p>
 * <p>
 * Dependencies injected into this service include:
 * - {@link UserService} for user-related operations.
 * - {@link JwtService} for generating and managing JWT tokens.
 * - {@link PasswordEncoder} for encoding user passwords.
 * - {@link AuthenticationManager} for authenticating user credentials.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * User registration
     *
     * @param request user data
     * @return token
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userService.createUser(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * User Authentication
     *
     * @param request user data
     * @return token
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        var user = userService.getUserByEmail(request.getEmail());

        var jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt);
        } catch (AuthenticationException ex) {
            // Handle authentication exception
            throw new AuthException("Authentication failed: " + ex.getMessage());
        }
    }

}
