package com.company.taskmanager.services.task;


import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskById(Long id);

    List<Task> getTasksByStatus(Status status);

    Task createTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    Task addUser(Long id, Long userId);
}
