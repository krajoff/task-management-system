package com.company.taskmanager.controllers.api;

import com.company.taskmanager.dtos.UserDto;
import com.company.taskmanager.models.user.User;
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
    private MappingUtils mappingUtils;

    @GetMapping()
    @Operation(summary = "Get a current user information")
    public UserDto getUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return mappingUtils
                .mapToUserDto(userService.getUserById(user.getId()));
    }

    @PutMapping()
    @Operation(summary = "Update a current user information")
    public UserDto updateUser(@RequestBody UserDto dto) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getPrincipal();
        authUser.setEmail(dto.getEmail());
        authUser.setTasks(dto.getTasks().stream()
                .map(mappingUtils::mapToTask).toList());
        return mappingUtils
                .mapToUserDto(userService.updateUser(authUser.getId(), authUser));
    }

    @DeleteMapping()
    @Operation(summary = "Delete a current user")
    public ResponseEntity<?> deleteUser() {
        try {
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();
            User authUser = (User) authentication.getPrincipal();
            userService.deleteUser(authUser.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
