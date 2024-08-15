package com.company.taskmanager.services.comment;

import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.repositories.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервисный класс для управления комментариями.
 * <p>
 * Этот сервис предоставляет методы:
 * - Получить комментарий по его уникальному идентификатору.
 * - Создать новый комментарий.
 * - Обновить существующий комментарий.
 * - Удалить комментарий по его идентификатору.
 * </p>
 * <p>
 * Сервис использует {@link CommentRepository} для взаимодействия с базой данных.
 * </p>
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Получает комментарий по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор комментария.
     * @return сущность комментария с указанным идентификатором.
     */
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    /**
     * Создает новый комментарий.
     *
     * @param comment сущность комментария, которая должна быть создана.
     * @return созданная сущность комментария.
     */
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Обновляет существующий комментарий по ID.
     *
     * @param id      уникальный идентификатор обновляемого комментария.
     * @param comment сущность комментария с обновленной информацией.
     * @return обновленная сущность комментария.
     */
    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = getCommentById(id);
        existingComment.setText(comment.getText());
        return commentRepository.save(existingComment);
    }

    /**
     * Удаляет комментарий по ID.
     *
     * @param id уникальный идентификатор удаляемого комментария.
     */
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
