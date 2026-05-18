package com.easyLife.taskManagement.taskManagement.dto;

import com.easyLife.taskManagement.taskManagement.dto.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoLogin {
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private Set<Role> roles;
}
