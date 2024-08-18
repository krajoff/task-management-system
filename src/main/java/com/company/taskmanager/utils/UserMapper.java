package com.company.taskmanager.utils;

import com.company.taskmanager.dtos.UserDto;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public abstract class UserMapper {
    @Autowired
    protected UserService userService;

    public abstract UserDto userToUserDto(User user);
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "priority", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "authoredTasks", ignore = true)
    @Mapping(target = "comments", ignore = true)
    public abstract User userDtoToUser(UserDto userDto);

    public String mapUserToUsername(User user) {
        return user != null ? user.getUsername() : null;
    }

    public User mapUsernameToUser(String username) {
        return userService.getUserByUsername(username);
    }
}
