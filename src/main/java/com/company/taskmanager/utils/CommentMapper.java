package com.company.taskmanager.utils;

import com.company.taskmanager.dtos.CommentDto;
import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.user.UserService;
import com.company.taskmanager.services.task.TaskService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {
    @Autowired
    protected UserService userService;

    @Autowired
    protected TaskService taskService;

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.username", target = "username")
    public abstract CommentDto commentToCommentDto(Comment comment);

    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "username", target = "user.username")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    public abstract Comment commentDtoToComment(CommentDto commentDto);

    @Named("userToUsername")
    public String mapUserToUsername(User user) {
        return user != null ? user.getUsername() : null;
    }

    public User mapUsernameToUser(String username) {
        return userService.getUserByUsername(username);
    }

    @Named("taskIdToTask")
    public Task taskIdToTask(Long taskId) {
        return taskService.getTaskById(taskId);
    }
}
