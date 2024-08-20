package com.company.taskmanager.dtos;

import lombok.Data;

/**
 * DTO (Data Transfer Object) для представления комментария.
 * Содержит id задачи, текст и username пользователя, оставившего комментрий.
 */
@Data
public class CommentDto {
    private Long id;
    private Long taskId;
    private String text;
    private String username;
}
