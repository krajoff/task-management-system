package com.company.taskmanager.dtos;

import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private List<TaskDto> tasks;
}
