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

    @PostMapping
    @Operation(summary = "Create a new user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user information")
    public User getUserByUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userService.getUserById(user.getId());
    }

    @PutMapping("/{username}")
    @Operation(summary = "Update an user by username")
    public User updateUser(@PathVariable String username,
                           @RequestBody User user) {
        return userService.updateByUsername(username, user);
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete an user by username")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUserByUsername(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
