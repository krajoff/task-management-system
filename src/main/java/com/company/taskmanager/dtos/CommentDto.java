package com.company.taskmanager.dtos;

import com.company.taskmanager.models.task.Task;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String text;
    private TaskDto task;
    private String username;
}
