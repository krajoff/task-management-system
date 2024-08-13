package com.company.taskmanager.services.user;

import com.company.taskmanager.exceptions.ResourceNotFoundException;
import com.company.taskmanager.models.user.Role;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing users.
 * This service provides methods to perform
 * CRUD operations on User entities.
 */
@Service
public class UserServiceImpl implements
        UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id an unique ID of the user.
     * @return the user with the specified ID.
     * @throws ResourceNotFoundException
     * if the user with the specified ID does not exist.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User with id " + id + " not found"));
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user.
     * @return the user with the specified username,
     * or null if no user is found.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user.
     * @return the user with the specified email address.
     * @throws ResourceNotFoundException if the user with
     * the specified email does not exist.
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User with email " + email + " not found"));
    }

    /**
     * Creates a new user.
     *
     * @param user the user entity to be created.
     * @return the created user.
     * @throws RuntimeException if the user with the same username
     * or email already exists.
     */
    public User createUser(User user) {
        if (!userRepository.findByUsernameOrEmail
                (user.getUsername(), user.getEmail()).isEmpty()) {
            throw new RuntimeException("User already existed");
        }
        return saveUser(user);
    }

    /**
     *  Updates an existing user by their ID.
     *
     * @param id an unique ID of the user.
     * @param user the user entity with updated information.
     * @return the updated user.
     */
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return existingUser;
    }

    /**
     * Updates an existing user by their username.
     *
     * @param username the username of the user to update.
     * @param user the user entity with update information.
     * @return the updated user.
     */
    public User updateByUsername(String username, User user) {
        User existingUser = getUserByUsername(username);
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return existingUser;
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Deletes a user by their username.
     *
     * @param username the username of the user to delete.
     */
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    /**
     * Save a user entity to the repository
     *
     * @param user the user entity to save
     * @return the saved user.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Provides a UserServiceDetailsService that loads user data by username.
     *
     * @return a UserDetailsService instance.
     */
    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the currently authenticated user.
     */
    public User getCurrentUser() {
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return getUserByUsername(username);
    }

    /**
     * Loads user details by their email address.
     *
     * @param email the email address of the user.
     * @return the UserDetails of the user.
     * @throws ResourceNotFoundException if the user
     * with the specified email does not exist.
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User with email " + email + " not found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .role(Role.USER)
                .build();
    }
}
