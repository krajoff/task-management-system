package com.company.taskmanager.exceptions;

import com.company.taskmanager.models.errors.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler — это глобальный обработчик исключений, который перехватывает
 * различные исключения в приложении и возвращает соответствующие HTTP-ответы с информацией
 * об ошибке и статусами.
 *
 * <p>Этот класс использует аннотацию {@link ControllerAdvice} для глобального перехвата
 * исключений, выбрасываемых в контроллерах. Каждое исключение обрабатывается
 * соответствующим методом, который возвращает объект {@link ResponseEntity}, содержащий
 * информацию об ошибке в виде {@link AppError} и HTTP-статус.</p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключение {@link ResourceNotFoundException}, которое
     * возникает, когда запрашиваемый ресурс не найден.
     *
     * @param e исключение {@link ResourceNotFoundException}
     * @return ответ с кодом 404 NOT FOUND и сообщением об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException
            (ResourceNotFoundException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(),
                        e.getMessage()), HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключение {@link AuthException}, которое
     * возникает при ошибке аутентификации.
     *
     * @param e исключение {@link AuthException}
     * @return ответ с кодом 401 UNAUTHORIZED и сообщением об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<AppError> catchAuthException
            (AuthException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Обрабатывает исключение {@link JwtAuthException}, которое возникает
     * при ошибке проверки JWT-токена.
     *
     * @param e исключение {@link JwtAuthException}
     * @return ответ с кодом 401 UNAUTHORIZED и сообщением об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<AppError> catchJwtAuthException
            (JwtAuthException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Обрабатывает исключение {@link JwtExpiredException}, которое возникает,
     * когда срок действия JWT-токена истек.
     *
     * @param e исключение {@link JwtExpiredException}
     * @return ответ с кодом 401 UNAUTHORIZED и сообщением об истечении срока
     * действия токена
     */
    @ExceptionHandler
    public ResponseEntity<AppError> catchJwtExpiredException
            (JwtExpiredException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Обрабатывает исключение {@link UsernameNotFoundException},
     * которое возникает, если пользователь не найден.
     *
     * @param e исключение {@link UsernameNotFoundException}
     * @return ответ с кодом 404 NOT FOUND и сообщением об ошибке
     */
    @ExceptionHandler
    public ResponseEntity<AppError> catchUserNotFoundException
            (UsernameNotFoundException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(),
                        e.getMessage()), HttpStatus.NOT_FOUND);
    }


}
