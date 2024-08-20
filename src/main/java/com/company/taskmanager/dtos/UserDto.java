package com.company.taskmanager.dtos;

import lombok.Data;

import java.util.List;

/**
 * DTO (Data Transfer Object) для передачи данных о пользователе.
 * Содержит основную информацию о пользователе, используемую для
 * обмена данными между слоями приложения.
 */
@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private List<TaskDto> tasks;
}
