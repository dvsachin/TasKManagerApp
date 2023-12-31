package com.manager.taskmanager_spring.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateTaskDTO {
    String description;
    String deadline;
    Boolean completed;
}
