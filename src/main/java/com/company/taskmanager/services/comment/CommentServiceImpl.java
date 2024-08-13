package com.company.taskmanager.services.comment;

import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.repositories.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing comments within the application.
 * <p>
 * This service provides methods to:
 * - Retrieve a comment by its unique ID.
 * - Create a new comment.
 * - Update an existing comment.
 * - Delete a comment by its ID.
 * </p>
 * <p>
 * The service uses {@link CommentRepository} for database interactions.
 * </p>
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Retrieves a comment by its unique ID.
     *
     * @param id the unique ID of the comment.
     * @return the comment entity with the specified ID.
     * @throws RuntimeException if the comment with the
     * specified ID is not found.
     */
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    /**
     * Creates a new comment.
     *
     * @param comment the comment entity to be created.
     * @return the created comment entity.
     */
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Updates an existing comment identified by its ID.
     *
     * @param id the unique ID of the comment to be updated.
     * @param comment the comment entity with updated information.
     * @return the updated comment entity.
     * @throws RuntimeException if the comment with the specified
     * ID is not found.
     */
    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = getCommentById(id);
        existingComment.setText(comment.getText());
        return commentRepository.save(existingComment);
    }

    /**
     * Deletes a comment identified by its ID.
     *
     * @param id the unique ID of the comment to be deleted.
     */
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
