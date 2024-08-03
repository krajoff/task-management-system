package com.company.taskmanager.services.comment;

import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.repositories.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = getCommentById(id);
        existingComment.setText(comment.getText());
        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
