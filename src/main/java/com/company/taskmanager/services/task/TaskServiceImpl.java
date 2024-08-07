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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    public Task getTaskById(Long id) {
        return taskRepository.
                findById(id).orElseThrow(() ->
                        new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByAuthor(user);
    }

    public List<Task> getTasksByUsername(String username) {
        User user = userService.getUserByUsername(username);
        return taskRepository.findByAuthor(user);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Task exsitingTask = getTaskById(id);
        exsitingTask.setDescription(task.getDescription());
        exsitingTask.setStatus(task.getStatus());
        exsitingTask.setExecutors(task.getExecutors());
        return taskRepository.save(exsitingTask);
    }

    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    public Task addUser(Long id, Long userId) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserById(userId);
        existingTask.addExecutor(user);
        return taskRepository.save(existingTask);
    }

    public Task addUser(Long id, String email) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserByEmail(email);
        existingTask.addExecutor(user);
        return taskRepository.save(existingTask);
    }

    public Task deleteUser(Long id, Long userId) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserById(userId);
        existingTask.deleteExecutor(user);
        return taskRepository.save(existingTask);
    }

    public Task deleteUser(Long id, String email) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserByEmail(email);
        existingTask.deleteExecutor(user);
        return taskRepository.save(existingTask);
    }

    public Task addComment(Long id, Long commentId) {
        Task existingTask = getTaskById(id);
        Comment comment = commentService.getCommentById(commentId);
        existingTask.addComment(comment);
        return taskRepository.save(existingTask);
    }

    public Task removeComment(Long id, Long commentId) {
        Task existingTask = getTaskById(id);
        Comment comment = commentService.getCommentById(commentId);
        existingTask.removeComment(comment);
        return taskRepository.save(existingTask);
    }

}
