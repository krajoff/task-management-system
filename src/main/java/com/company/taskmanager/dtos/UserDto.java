package com.company.taskmanager.dtos;

import com.company.taskmanager.models.task.Task;
import com.company.taskmanager.models.user.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String username;
    private Role role;
    private String email;
    private Date createdAt;
    private Date updatedAt;
    private List<Task> tasks;
}
