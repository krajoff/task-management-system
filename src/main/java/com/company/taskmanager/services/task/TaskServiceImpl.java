package com.company.taskmanager.services.task;

import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.repositories.task.TaskRepository;
import com.company.taskmanager.services.comment.CommentService;
import com.company.taskmanager.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing tasks.
 * <p>
 * This service provides methods to perform CRUD
 * operations on Task entities and manage task assignments and comments.
 * </p>
 * <p>
 * Dependencies injected into this service include:
 * - {@link TaskRepository} for database interactions with tasks.
 * - {@link UserService} for user-related operations.
 * - {@link CommentService} for comment-related operations.
 * </p>
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    /**
     * Retrieves a task by its unique ID.
     *
     * @param id an unique ID of the task.
     * @return the task entity with specified ID.
     * @throws RuntimeException if the task with the specified ID not found.
     */
    public Task getTaskById(Long id) {
        return taskRepository.
                findById(id).orElseThrow(() ->
                        new RuntimeException("Task not found"));
    }

    /**
     * Retrieves a list of tasks with a specific status.
     *
     * @param status the status of the tasks to retrieve.
     * @return a list of tasks with the specified status.
     */
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    /**
     * Retrieves a list of tasks assigned to a specific user.
     *
     * @param user the user whose tasks to retrieve.
     * @return a list of tasks assigned to the specified user.
     */
    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByAuthor(user);
    }

    /**
     * Retrieves a list of tasks assigned to a user by their username.
     *
     * @param username the username of the user whose tasks to retrieve.
     * @return a list of tasks assigned to the user with the specified username.
     */
    public List<Task> getTasksByUsername(String username) {
        User user = userService.getUserByUsername(username);
        return taskRepository.findByAuthor(user);
    }

    /**
     * Retrieves a list of tasks where the specified user is an executor.
     *
     * @param username the username of the executor.
     * @return a list of tasks where the user is an executor.
     */
    public List<Task> getTasksByExecutor(String username){
        User user = userService.getUserByUsername(username);
        return taskRepository.findByExecutorsContaining(user);
    }

    /**
     * Creates a new task.
     *
     * @param task the task entity to be created.
     * @return the created task.
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Updates an existing task by its ID.
     *
     * @param id the ID of the task to update.
     * @param task the task entity with updated information.
     * @return the updated task.
     */
    public Task updateTask(Long id, Task task) {
        Task existingTask = getTaskById(id);
        if (task.getStatus() != null) {
            existingTask.setStatus(task.getStatus());
        }
        if (task.getExecutors() != null) {
            existingTask.setExecutors(task.getExecutors());
        }
        return taskRepository.save(existingTask);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete.
     */
    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    /**
     * Adds a user as an executor to a task.
     *
     * @param id the ID of the task.
     * @param userId the ID of the user to add as an executor.
     * @return the updated task with the new executor.
     */
    public Task addUser(Long id, Long userId) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserById(userId);
        existingTask.addExecutor(user);
        return taskRepository.save(existingTask);
    }

    /**
     * Adds a user as an executor to a task by their email.
     *
     * @param id the ID of the task.
     * @param email the email of the user to add as an executor.
     * @return the updated task with the new executor.
     */
    public Task addUser(Long id, String email) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserByEmail(email);
        existingTask.addExecutor(user);
        return taskRepository.save(existingTask);
    }

    /**
     * Removes a user from the executors of a task.
     *
     * @param id the ID of the task.
     * @param userId the ID of the user to remove from executors.
     * @return the updated task without the specified executor.
     */
    public Task deleteUser(Long id, Long userId) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserById(userId);
        existingTask.deleteExecutor(user);
        return taskRepository.save(existingTask);
    }

    /**
     * Removes a user from the executors of a task by their email.
     *
     * @param id the ID of the task.
     * @param email the email of the user to remove from executors.
     * @return the updated task without the specified executor.
     */
    public Task deleteUser(Long id, String email) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserByEmail(email);
        existingTask.deleteExecutor(user);
        return taskRepository.save(existingTask);
    }

    /**
     * Adds a comment to a task.
     *
     * @param id the ID of the task.
     * @param commentId the ID of the comment to add.
     * @return the updated task with the new comment.
     */
    public Task addComment(Long id, Long commentId) {
        Task existingTask = getTaskById(id);
        Comment comment = commentService.getCommentById(commentId);
        existingTask.addComment(comment);
        return taskRepository.save(existingTask);
    }

    /**
     * Removes a comment from a task.
     *
     * @param id the ID of the task.
     * @param commentId the ID of the comment to remove.
     * @return the updated task without the specified comment.
     */
    public Task removeComment(Long id, Long commentId) {
        Task existingTask = getTaskById(id);
        Comment comment = commentService.getCommentById(commentId);
        existingTask.removeComment(comment);
        return taskRepository.save(existingTask);
    }

}
