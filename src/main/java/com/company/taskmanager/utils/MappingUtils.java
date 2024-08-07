package com.company.taskmanager.utils;

import com.company.taskmanager.dtos.TaskDto;
import com.company.taskmanager.dtos.UserDto;
import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.task.Priority;
import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.task.TaskService;
import com.company.taskmanager.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MappingUtils {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    /**
     * Convert from entity to dto
     *
     * @return UserDto
     */
    public UserDto mapToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setTasks(user.getTasks().stream()
                .map(this::mapToTaskDto)
                .toList());
        return dto;
    }

    /**
     * Convert from dto to entity
     *
     * @return User
     */
    public User mapToUser(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setTasks(dto.getTasks().stream().map(this::mapToTask).toList());
        return user;
    }


    /**
     * Convert from entity to dto
     *
     * @return TaskDto
     */
    public TaskDto mapToTaskDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setAuthor(task.getAuthor().getUsername());
        dto.setExecutors(task.getExecutors()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toSet()));
        dto.setComments(task.getComments());
        return dto;
    }

    /**
     * Convert from dto to entity
     *
     * @return Task
     */
    public Task mapToTask(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setAuthor(userService.getUserByUsername(dto.getAuthor()));
        task.setExecutors(dto.getExecutors()
                .stream()
                .map(userService::getUserByUsername)
                .collect(Collectors.toSet()));
        task.setComments(task.getComments());
        return task;
    }

}
