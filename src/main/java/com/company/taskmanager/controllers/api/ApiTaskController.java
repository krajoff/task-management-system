package com.company.taskmanager.controllers.api;

import com.company.taskmanager.dtos.StatusDto;
import com.company.taskmanager.dtos.TaskDto;
import com.company.taskmanager.exceptions.ResourceNotFoundException;
import com.company.taskmanager.models.task.Status;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.auth.AuthService;
import com.company.taskmanager.services.task.TaskService;
import com.company.taskmanager.services.user.UserService;
import com.company.taskmanager.utils.MappingUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Task", description = "The Task API")
@RestController
@RequestMapping("/api/task")
public class ApiTaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private MappingUtils mappingUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<TaskDto> getTasks() {
        User user = authService.getCurrentUser();
        return taskService.getTasksByUser(user)
                .stream().map(m ->
                        mappingUtils.mapToTaskDto(m)).toList();
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto dto) {
        User user = authService.getCurrentUser();
        Task task = mappingUtils.mapToTask(dto);
        task.setAuthor(user);
        return mappingUtils.mapToTaskDto(taskService.createTask(task));
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        return mappingUtils.mapToTaskDto(taskService.getTaskById(id));
    }

    @GetMapping("/username/{username}")
    public List<TaskDto> getTaskByUsername(@PathVariable String username) {
        return taskService
                .getTasksByUsername(username)
                .stream()
                .map(mappingUtils::mapToTaskDto).toList();
    }

    @GetMapping("/executor/{username}")
    public List<TaskDto> getTaskByExecutor(@PathVariable String username) {
        return taskService
                .getTasksByExecutor(username)
                .stream()
                .map(mappingUtils::mapToTaskDto).toList();
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id,
                              @RequestBody TaskDto dto) {
        User user = authService.getCurrentUser();
        Task existingTask = taskService.getTaskById(id);
        if (existingTask.getAuthor().equals(user)) {
            taskService.updateTask(id, mappingUtils.mapToTask(dto));
        }
        return getTaskById(id);
    }

    @PutMapping("/{id}/status/{status}")
    public TaskDto updateStatus(@PathVariable Long id,
                                @PathVariable String status) {
        User user = authService.getCurrentUser();
        Task existingTask = taskService.getTaskById(id);
        if (existingTask.getExecutors().contains(user)) {
            existingTask.setStatus(Status.valueOf(status));
            taskService.updateTask(id, existingTask);
        }
        return getTaskById(id);
    }

    @PutMapping("/{id}/executor/{username}")
    public TaskDto addExecutorByUsername(@PathVariable Long id,
                                         @PathVariable String username) {
        User user = authService.getCurrentUser();
        Task task = taskService.getTaskById(id);
        User executor = userService.getUserByUsername(username);
        if (task.getAuthor().equals(user) && executor != null) {
            task.addExecutor(executor);
            return mappingUtils
                    .mapToTaskDto(taskService.updateTask(id, task));
        }
        throw new ResourceNotFoundException("User not found");
    }

    @DeleteMapping("/{id}/executor/{username}")
    public TaskDto deleteExecutorByUsername(@PathVariable Long id,
                                            @PathVariable String username) {
        User user = authService.getCurrentUser();
        Task task = taskService.getTaskById(id);
        User executor = userService.getUserByUsername(username);
        if (task.getAuthor().equals(user) && executor != null) {
            task.deleteExecutor(executor);
            return mappingUtils
                    .mapToTaskDto(taskService.updateTask(id, task));
        }
        throw new ResourceNotFoundException("User not found");
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
