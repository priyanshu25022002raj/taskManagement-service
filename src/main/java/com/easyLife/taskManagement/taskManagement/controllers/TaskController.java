package com.easyLife.taskManagement.taskManagement.controllers;

import com.easyLife.taskManagement.taskManagement.dto.TaskDto;
import com.easyLife.taskManagement.taskManagement.repositories.TaskRepository;
import com.easyLife.taskManagement.taskManagement.services.TaskService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.rmi.server.LogStream.log;

@RestController
@RequestMapping(path = "/taskApp")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    //@Secured("ROLE_USER")
    @PostMapping(path = "/createTask")
     public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskDto taskDto){
        TaskDto taskDto1 = taskService.create(taskDto);
        return ResponseEntity.ok(taskDto1);
    }

    //@Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping(path = "/userTask")
    public ResponseEntity<List<TaskDto>> getUserTask(){
        List<TaskDto> taskDtoList = taskService.getUserTask();
        return ResponseEntity.ok(taskDtoList);
    }


    //@PreAuthorize("hasRole('USER')")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping(path = "/userTask/{taskId}")
    public ResponseEntity<TaskDto> getUserTaskByTaskId(@PathVariable Long taskId){
        System.out.println("ENtering to the method");
        System.out.println("User role is: "+ SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        TaskDto taskDtoList = taskService.getUserTaskByTaskId(taskId);
        return ResponseEntity.ok(taskDtoList);
    }

    //@Secured({"ROLE_USER","ROLE_ADMIN"})
    @PutMapping(path = "/userTask/updateDuedate/{taskId}")
    public ResponseEntity<TaskDto> updateDueDateOfTaskByTaskId(@PathVariable Long taskId,@RequestBody Map<String, LocalDate> request){
        LocalDate dueDate = request.get("dueDate");
        TaskDto taskDtoList = taskService.updateDueDateOfTaskByTaskId(taskId,dueDate);
        return ResponseEntity.ok(taskDtoList);
    }

    //@Secured({"ROLE_USER","ROLE_ADMIN"})
    @PutMapping(path = "/userTask/updateTaskCompleted/{taskId}")
    public ResponseEntity<TaskDto> updateTaskCompletedOfTaskByTaskId(@PathVariable Long taskId, @RequestBody Map<String, Boolean> request){
        boolean taskCompleted = request.get("taskCompleted");
        TaskDto taskDtoList = taskService.updateTaskCompletedOfTaskByTaskId(taskId,taskCompleted);
        return ResponseEntity.ok(taskDtoList);
    }


}
