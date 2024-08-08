package com.company.taskmanager.controllers.api;

import com.company.taskmanager.dtos.CommentDto;
import com.company.taskmanager.dtos.TaskDto;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.auth.AuthService;
import com.company.taskmanager.services.task.TaskService;
import com.company.taskmanager.services.user.UserService;
import com.company.taskmanager.utils.MappingUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comments", description = "The Comments API")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private MappingUtils mappingUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<CommentDto> getComments() {
        User user = authService.getCurrentUser();
        return taskService.getTasksByUser(user)
                .stream().map(m ->
                        mappingUtils.mapToTaskDto(m)).toList();
    }

    @PostMapping("/{id_task}")
    public TaskDto createComment(@PathVariable Long id
            @RequestBody CommentDto dto) {
        User user = authService.getCurrentUser();
        Task task = taskService.getTaskById(id);
        task.addComment(mappingUtils.);
        task.setAuthor(user);
        return mappingUtils.mapToTaskDto(taskService.createTask(task));
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        return mappingUtils.mapToTaskDto(taskService.getTaskById(id));
    }


}
