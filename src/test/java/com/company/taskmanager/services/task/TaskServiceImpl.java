package com.company.taskmanager.services.task;

import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.repositories.task.TaskRepository;
import com.company.taskmanager.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.
                findById(id).orElseThrow(() ->
                        new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Task exsitingTask = getTaskById(id);
        exsitingTask.setDescription(task.getDescription());
        exsitingTask.setStatus(task.getStatus());
        return taskRepository.save(exsitingTask);
    }

    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    public Task addUser(Long id, Long userId) {
        Task existingTask = getTaskById(id);
        User user = userService.getUserById(userId);
        existingTask.addUser(user);
        return taskRepository.save(existingTask);
    }
}
