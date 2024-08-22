package com.company.taskmanager.dtos;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO (Data Transfer Object) для представления комментария.
 * Содержит id задачи, текст и username пользователя, оставившего комментрий.
 */
@Data
@Schema(description = "DTO для комментариев, привязанных к задачам")
public class CommentDto {

    @Schema(description = "Уникальный идентификатор комментария", example = "1")
    private Long id;

    @Schema(description = "Идентификатор задачи, к которой относится комментарий", example = "10")
    private Long taskId;

    @Schema(description = "Текст комментария", example = "Это пример комментария")
    private String text;

    @Schema(description = "Имя пользователя, оставившего комментарий", example = "john_doe")
    private String username;
}
