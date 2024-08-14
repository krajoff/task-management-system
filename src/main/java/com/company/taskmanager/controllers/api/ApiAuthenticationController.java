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

/**
 * Контроллер, предоставляющий API для аутентификации пользователей.
 * <p>
 * Этот контроллер отвечает за регистрацию новых пользователей и
 * аутентификацию существующих.
 * </p>
 */
@Tag(name = "Authentication", description = "The Authentication API")
@RequestMapping("/api/auth")
@RestController
public class ApiAuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    /**
     * Конструктор для создания экземпляра контроллера аутентификации.
     *
     * @param jwtService            сервис для работы с JWT-токенами
     * @param authenticationService сервис для аутентификации пользователей
     */
    public ApiAuthenticationController(JwtService jwtService,
                                       AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    /**
     * Регистрация нового пользователя.
     * <p>
     * При успешной регистрации возвращает JWT-токен для нового пользователя.
     * </p>
     *
     * @param request объект запроса с данными для регистрации
     * @return ответ с JWT-токеном
     */
    @PostMapping("/signup")
    public JwtAuthenticationResponse register(
            @RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    /**
     * Аутентификация пользователя.
     * <p>
     * При успешной аутентификации возвращает JWT-токен для пользователя.
     * </p>
     *
     * @param request объект запроса с данными для входа
     * @return ответ с JWT-токеном
     */
    @PostMapping("/login")
    public JwtAuthenticationResponse authenticate(
            @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
