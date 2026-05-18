package com.easyLife.taskManagement.taskManagement.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    @FutureOrPresent(message = "The due date must be today or in the future")
    private LocalDate dueDate;
    private boolean taskCompleted;
    private Integer priority;
    // The below field should not be editable by the user.
//    @NotNull(message = "User ID is required to assign the task")
//    private Long userId;
}
