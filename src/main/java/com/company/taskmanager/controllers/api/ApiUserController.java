package com.company.taskmanager.controllers.api;

import com.company.taskmanager.dtos.UserDto;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.auth.AuthService;
import com.company.taskmanager.services.task.TaskService;
import com.company.taskmanager.services.user.UserService;
import com.company.taskmanager.utils.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер, предоставляющий API для управления пользователями.
 * <p>
 * Этот контроллер предоставляет методы для получения, обновления и удаления
 * информации о текущем пользователе.
 * </p>
 */
@Tag(name = "User", description = "The Users API")
@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthService authService;

    /**
     * Получает информацию о текущем пользователе.
     *
     * @return DTO с информацией о текущем пользователе
     */
    @GetMapping()
    @Operation(summary = "Get a current user information")
    public UserDto getUser() {
        User user = authService.getCurrentUser();
        user.setTasks(taskService
                .getTasksByUser(user, Pageable.unpaged()).getContent());
        return userMapper.userToUserDto(user);
    }

    /**
     * Обновляет информацию о текущем пользователе.
     *
     * @param userDto DTO с обновленной информацией о пользователе
     * @return обновленная информация о пользователе в виде DTO
     */
    @PutMapping()
    @Operation(summary = "Update a current user information")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = authService.getCurrentUser();
        user.setEmail(userDto.getEmail());
        userService.updateUser(user.getId(), user);
        return userDto;
    }

    /**
     * Удаляет текущего пользователя.
     *
     * @return HTTP-ответ с кодом состояния OK при успешном удалении
     * или NOT_FOUND при возникновении ошибки
     */
    @DeleteMapping()
    @Operation(summary = "Delete a current user")
    public ResponseEntity<?> deleteUser() {
        try {
            User user = authService.getCurrentUser();
            userService.deleteUser(user.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
