package com.company.taskmanager.controllers.api;

import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.task.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping
    public List<Task> getTasks() {
        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();
        User user = (User) authentication.getPrincipal();
        return taskService.getTasksByUser(user);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        task.setUser(user);
        return taskService.createTask(task);
    }

    @GetMapping("/{task_id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{task_id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
