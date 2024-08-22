package com.company.taskmanager.dtos;

import com.company.taskmanager.models.task.Priority;
import com.company.taskmanager.models.task.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DTO (Data Transfer Object) для представления задачи.
 * Содержит информацию о задаче, такую как идентификатор,
 * заголовок, описание, статус, приоритет,
 * автор, исполнители и комментарии.
 */
@Data
@Schema(description = "DTO для задачи")
public class TaskDto {

    @Schema(description = "Уникальный идентификатор задачи", example = "1")
    private Long id;

    @Schema(description = "Название задачи", example = "Разработка новой функции")
    private String title;

    @Schema(description = "Подробное описание задачи",
            example = "Необходимо разработать и протестировать новую функцию для продукта")
    private String description;

    @Schema(description = "Текущий статус задачи", example = "IN_PROGRESS")
    private Status status;

    @Schema(description = "Приоритет задачи", example = "HIGH")
    private Priority priority;

    @Schema(description = "Имя автора задачи (username)", example = "Nikolay")
    private String author;

    @Schema(description = "Список исполнителей задачи",
            example = "[\"Pavel\", \"Nikita\"]")
    private Set<String> executors = new HashSet<>();

    @Schema(description = "Комментарии, связанные с задачей")
    private List<CommentDto> comments = new ArrayList<>();
}
