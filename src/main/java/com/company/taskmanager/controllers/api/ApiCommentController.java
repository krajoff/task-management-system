package com.company.taskmanager.controllers.api;

import com.company.taskmanager.dtos.CommentDto;
import com.company.taskmanager.dtos.TaskDto;
import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.auth.AuthService;
import com.company.taskmanager.services.task.TaskService;
import com.company.taskmanager.utils.MappingUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comments", description = "The Comments API")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private MappingUtils mappingUtils;
    @Autowired
    private AuthService authService;

    @PutMapping("/task_id/{id_task}")
    public TaskDto createComment(@PathVariable Long id_task,
                                 @RequestBody CommentDto dto) {
        User user = authService.getCurrentUser();
        Task task = taskService.getTaskById(id_task);
        dto.setUsername(user.getUsername());
        dto.setTaskId(id_task);
        Comment comment = mappingUtils.mapToComment(dto);
        task.addComment(comment);
        return mappingUtils
                .mapToTaskDto(taskService.updateTask(id_task, task));
    }
}
