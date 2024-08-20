package com.company.taskmanager.utils;

import com.company.taskmanager.dtos.CommentDto;
import com.company.taskmanager.models.comment.Comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Маппер для преобразования между сущностями Comment и CommentDto.
 * Используется для упрощения преобразования данных между слоями приложения.
 */
@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    /**
     * Преобразует сущность Comment в CommentDto
     *
     * @param comment сущность Comment
     * @return Comment
     */
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.username", target = "username")
    public abstract CommentDto commentToCommentDto(Comment comment);

    /**
     * Преобразует сущность CommentDto в Comment
     *
     * @param commentDto сущность CommentDto
     * @return Comment
     */
    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "username", target = "user.username")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    public abstract Comment commentDtoToComment(CommentDto commentDto);

}
