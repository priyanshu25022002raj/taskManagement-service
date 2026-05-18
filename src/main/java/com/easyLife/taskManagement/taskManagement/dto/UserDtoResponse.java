package com.easyLife.taskManagement.taskManagement.dto;

import com.easyLife.taskManagement.taskManagement.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {
    private Long id;
    private String email;
    private Set<Role> roles;
}
