package com.company.taskmanager.controllers.api;

import com.company.taskmanager.dtos.UserDto;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.auth.AuthService;
import com.company.taskmanager.services.task.TaskService;
import com.company.taskmanager.services.user.UserService;
import com.company.taskmanager.utils.MappingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Tag(name = "User", description = "The Users API")
@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private MappingUtils mappingUtils;
    @Autowired
    private AuthService authService;

    @GetMapping()
    @Operation(summary = "Get a current user information")
    public UserDto getUser() {
        User user = authService.getCurrentUser();
        user.setTasks(taskService.getTasksByUser(user));
        return mappingUtils.mapToUserDto(user);
    }

    @PutMapping()
    @Operation(summary = "Update a current user information")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = authService.getCurrentUser();
        user.setEmail(userDto.getEmail());
        userService.updateUser(user.getId(), user);
        return userDto;
    }

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
