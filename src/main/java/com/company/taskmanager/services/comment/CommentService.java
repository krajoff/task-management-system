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

    /**
     * Получает комментарий по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор комментария
     * @return {@link Comment} с указанным идентификатором,
     * или {@code null}, если комментарий не найден
     */
    Comment getCommentById(Long id);

    /**
     * Создает новый комментарий.
     *
     * @param comment сущность {@link Comment} для создания
     * @return созданный комментарий {@link Comment}
     */
    Comment createComment(Comment comment);

    /**
     * Обновляет информацию о существующем комментарии.
     *
     * @param id уникальный идентификатор комментария
     * @param comment обновленные данные комментария
     * @return обновленный комментарий {@link Comment}
     */
    Comment updateComment(Long id, Comment comment);

    /**
     * Удаляет комментарий по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор комментария
     */
    void deleteComment(Long id);
}
