package com.company.taskmanager.services.task;


import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;

import java.util.List;

public interface TaskService {
    Task getTaskById(Long id);

    List<Task> getTasksByStatus(Status status);

    Task createTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    Task addExecutor(Long id, Long userId);

    Task addExecutor(Long id, String email);

    Task deleteExecutor(Long id, Long userId);

    Task deleteExecutor(Long id, String email);

    Task addComment(Long id, Long commentId);

    Task removeComment(Long id, Long commentId);

}
