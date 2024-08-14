package com.company.taskmanager.services.comment;

import com.company.taskmanager.models.comment.Comment;

/**
 * Сервисный интерфейс для управления комментариями.
 * <p>
 * Этот сервис определяет методы для выполнения
 * CRUD-операций над сущностями {@link Comment}.
 * </p>
 */
public interface CommentService {

    Comment getCommentById(Long id);

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment comment);

    void deleteComment(Long id);
}
