package com.company.taskmanager.dtos;

import com.company.taskmanager.models.task.Task;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long taskId;
    private String text;
    private String username;
}
