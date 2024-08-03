package com.company.taskmanager.services.user;

import com.company.taskmanager.models.user.User;
import com.company.taskmanager.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        if (!userRepository.findByUsernameOrEmail
                (user.getUsername(), user.getEmail()).isEmpty()) {
            throw new RuntimeException("User already existed");
        }
        return saveUser(user);
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return existingUser;
    }

    public User updateByUsername(String username, User user) {
        User existingUser = getUserByUsername(username);
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return existingUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
