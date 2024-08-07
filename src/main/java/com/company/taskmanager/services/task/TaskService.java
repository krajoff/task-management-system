package com.company.taskmanager.services.task;


import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;

import java.util.List;

public interface TaskService {
    Task getTaskById(Long id);

    List<Task> getTasksByStatus(Status status);

    List<Task> getTasksByUser(User user);

    List<Task> getTasksByUsername(String username);

    Task createTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    Task addUser(Long id, Long userId);

    Task addUser(Long id, String email);

    Task deleteUser(Long id, Long userId);

    Task deleteUser(Long id, String email);

    Task addComment(Long id, Long commentId);

    Task removeComment(Long id, Long commentId);

}
