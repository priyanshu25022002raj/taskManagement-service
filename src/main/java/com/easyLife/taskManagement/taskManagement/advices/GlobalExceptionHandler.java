package com.easyLife.taskManagement.taskManagement.advices;

import com.easyLife.taskManagement.taskManagement.exceptions.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.security.access.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentException.class)
    public ResponseEntity<ApiResponse<?>> BookIsNotPresent(StudentException ex){
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).message(ex.getMessage())
                .build();
        ApiResponse apiResponse = ApiResponse.builder().apiError(apiError).build();
        return new ResponseEntity<>(apiResponse,apiError.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ApiResponse<?>> InternalServerError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).message("Check the code or request.")
                .subError(errors).build();
        ApiResponse apiResponse = ApiResponse.builder().apiError(apiError).build();
        return new ResponseEntity<>(apiResponse,apiError.getHttpStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public  ResponseEntity<ApiResponse<?>> AccessDeniedError(AccessDeniedException ex){
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.FORBIDDEN).message(ex.getMessage())
                .build();
        ApiResponse apiResponse = ApiResponse.builder().apiError(apiError).build();
        return new ResponseEntity<>(apiResponse,apiError.getHttpStatus());
    }

}
