package com.company.taskmanager.repositories.comment;

import com.company.taskmanager.models.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий для работы с сущностями комментарии.
 * <p>
 * Предоставляет стандартные CRUD-операции.
 * </p>
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
