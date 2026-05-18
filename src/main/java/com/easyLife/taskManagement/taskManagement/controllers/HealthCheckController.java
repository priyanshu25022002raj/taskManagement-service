package com.easyLife.taskManagement.taskManagement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthCheckController {

    @GetMapping(path = "/")
    public ResponseEntity<Object> healthCheckController(){
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
