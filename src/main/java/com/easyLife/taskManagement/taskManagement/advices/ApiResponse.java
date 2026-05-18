package com.easyLife.taskManagement.taskManagement.advices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    @Builder.Default
    private LocalDateTime timeStamp = LocalDateTime.now();
    private T data;
    private ApiError apiError;

    public ApiResponse(){
        this.timeStamp=LocalDateTime.now();
    }
    public ApiResponse(T data){
        this();
        this.data=data;
    }
    public ApiResponse(ApiError error){
        this();
        this.apiError=error;
    }
}
