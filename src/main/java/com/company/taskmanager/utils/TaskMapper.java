package com.company.taskmanager.utils;

import com.company.taskmanager.dtos.TaskDto;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.user.UserService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {
    @Autowired
    protected UserService userService;
    public abstract TaskDto taskToTaskDto(Task task);
    public abstract Task taskDtoToTask(TaskDto taskDto);

    public String mapUserToUsername(User user) {
        return user != null ? user.getUsername() : null;
    }
    public User mapUsernameToUser(String username) {
        return userService.getUserByUsername(username);
    }
}
