package com.easyLife.taskManagement.taskManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class USerLoginResponse {
    private String email;
    private String accessToken;

}
