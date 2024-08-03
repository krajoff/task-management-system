package com.company.taskmanager.services.comment;

import com.company.taskmanager.models.comment.Comment;

public interface CommentService {

    Comment getCommentById(Long id);

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment comment);

    void deleteComment(Long id);
}
