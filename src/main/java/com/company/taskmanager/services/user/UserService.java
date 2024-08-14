package com.company.taskmanager.services.user;

import com.company.taskmanager.models.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * Интерфейс для операций с пользовательскими сервисами.
 * Предоставляет методы для CRUD-операций над сущностями пользователей,
 * а также методы для аутентификации и получения информации о пользователе.
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
