package com.company.taskmanager.services.comment;

import com.company.taskmanager.models.comment.Comment;

/**
 * Service interface for managing comments.
 * <p>
 * This service defines methods for performing
 * CRUD operations on {@link Comment} entities.
 * </p>
 */
public interface CommentService {

    Comment getCommentById(Long id);

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment comment);

    void deleteComment(Long id);
}
