package com.company.taskmanager.controllers.api;

import com.company.taskmanager.dtos.CommentDto;
import com.company.taskmanager.dtos.TaskDto;
import com.company.taskmanager.models.comment.Comment;
import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.User;
import com.company.taskmanager.services.auth.AuthService;
import com.company.taskmanager.services.task.TaskService;
import com.company.taskmanager.utils.CommentMapper;
import com.company.taskmanager.utils.TaskMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер, предоставляющий API для работы с комментариями.
 * <p>
 * Этот контроллер позволяет создавать комментарии к задачам.
 * </p>
 */
@Tag(name = "Comments", description = "The Comments API")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private AuthService authService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TaskMapper taskMapper;

    /**
     * Создает новый комментарий для указанной задачи.
     * <p>
     * Комментарий добавляется к задаче, после чего обновленная
     * задача возвращается в виде DTO.
     * </p>
     *
     * @param id_task идентификатор задачи, к которой будет добавлен комментарий
     * @param dto     данные комментария
     * @return обновленный объект задачи в виде DTO
     */
    @PutMapping("/task_id/{id_task}")
    public TaskDto createComment(@PathVariable Long id_task,
                                 @RequestBody CommentDto dto) {
        User user = authService.getCurrentUser();
        Task task = taskService.getTaskById(id_task);
        dto.setUsername(user.getUsername());
        dto.setTaskId(id_task);
        Comment comment = commentMapper.commentDtoToComment(dto);
        task.addComment(comment);
        return taskMapper
                .taskToTaskDto(taskService.updateTask(id_task, task));
    }
}
