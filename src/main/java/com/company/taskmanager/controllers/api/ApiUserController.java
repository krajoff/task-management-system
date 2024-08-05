package com.company.taskmanager.controllers.api;

import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.user.UserService;
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

    @GetMapping()
    @Operation(summary = "Get a current user information")
    public User getUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userService.getUserById(user.getId());
    }

    @PutMapping()
    @Operation(summary = "Update a current user information")
    public User updateUser(@RequestBody User user) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getPrincipal();
        authUser.setEmail(user.getEmail());
        authUser.setTasks(user.getTasks());
        authUser.setPassword(user.getPassword());
        return userService.updateUser(authUser.getId(), authUser);
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
