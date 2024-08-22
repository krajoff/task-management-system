package com.company.taskmanager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO (Data Transfer Object) для представления статуса.
 */
@Data
@Schema(description = "DTO для представления статуса задачи")
public class StatusDto {

    @Schema(description = "Текущий статус задачи", example = "DONE")
    private String status;
}
