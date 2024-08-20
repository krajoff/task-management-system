package com.company.taskmanager.dtos;

import com.company.taskmanager.models.task.Priority;
import com.company.taskmanager.models.task.Status;
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
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String author;
    private Set<String> executors = new HashSet<>();
    private List<CommentDto> comments = new ArrayList<>();
}
