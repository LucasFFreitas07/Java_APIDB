package com.lff.api_db.dto;

import com.lff.api_db.domain.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDTO {
    @NotBlank(message="Deve haver um título para a tarefa")
    private String title;

    @NotBlank(message="Preencha o campo de descrição")
    private String description;

    private TaskStatus status = TaskStatus.IN_PROGRESS;
}

