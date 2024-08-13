package com.company.taskmanager.services.user;

import com.company.taskmanager.models.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * Interface for user service operations.
 * Provides methods for CRUD operations on User entities,
 * as well as methods for user authentication and retrieval.
 */
public interface UserService {

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    User updateByUsername(String username, User user);

    void deleteUser(Long id);

    void deleteUserByUsername(String username);

    User saveUser(User user);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    UserDetailsService userDetailsService();

    User getCurrentUser();
}
