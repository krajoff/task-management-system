package com.company.taskmanager.utils;

import com.company.taskmanager.dtos.TaskDto;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Маппер для преобразования между сущностями Task и TaskDto.
 * Используется для упрощения преобразования данных между слоями приложения.
 */
@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public abstract class TaskMapper {

    /**
     * Сервис для работы с пользователями.
     * Используется для получения информации о пользователях при преобразовании.
     */
    @Autowired
    protected UserService userService;

    /**
     * Преобразование сущности Task в TaskDto
     *
     * @param task сущность Task
     * @return TaskDto
     */
    @Mapping(source = "comments", target = "comments")
    @Mapping(source = "author.username", target = "author")
    @Mapping(source = "executors", target = "executors",
            qualifiedByName = "mapToDtoExecutors")
    public abstract TaskDto taskToTaskDto(Task task);

    /**
     * Преобразование сущности TaskDto в Task
     *
     * @param taskDto сущность TaskDto
     * @return Task
     */
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(source = "comments", target = "comments")
    @Mapping(source = "author", target = "author",
            qualifiedByName = "mapUsernameToUser")
    @Mapping(source = "executors", target = "executors",
            qualifiedByName = "mapToEntityExecutors")
    public abstract Task taskDtoToTask(TaskDto taskDto);

    /**
     * Преобразует username в сущность User.
     *
     * @param username имя пользователя
     * @return сущность User
     */
    @Named("mapUsernameToUser")
    public User mapUsernameToUser(String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * Преобразует набор сущностей User в набор строковых идентификаторов исполнителей.
     *
     * @param executors набор сущностей User
     * @return набор строковых идентификаторов исполнителей
     */
    @Named("mapToDtoExecutors")
    public Set<String> mapToDtoExecutors(Set<User> executors) {
        return executors.stream()
                .map(User::getUsername)
                .collect(Collectors.toSet());
    }

    /**
     * Преобразует набор строковых идентификаторов исполнителей в набор сущностей User.
     *
     * @param executors набор строковых идентификаторов исполнителей
     * @return набор сущностей User
     */
    @Named("mapToEntityExecutors")
    public Set<User> mapToEntityExecutors(Set<String> executors) {
        return executors.stream()
                .map(userService::getUserByUsername)
                .collect(Collectors.toSet());
    }


}
