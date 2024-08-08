package com.company.taskmanager.dtos;

import lombok.Data;

@Data
public class StatusDto {
    private String status;

    public StatusDto(String status) {
        this.status = status;
    }
}
