package com.company.taskmanager.controllers.api;

import com.company.taskmanager.requests.SignInRequest;
import com.company.taskmanager.requests.SignUpRequest;
import com.company.taskmanager.responces.JwtAuthenticationResponse;
import com.company.taskmanager.services.jwt.AuthenticationService;
import com.company.taskmanager.services.jwt.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Authentication", description = "The Authentication API")
@RequestMapping("/api/auth")
@RestController
public class ApiAuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public ApiAuthenticationController(JwtService jwtService,
                                       AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public JwtAuthenticationResponse register(
            @RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse authenticate(
            @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
