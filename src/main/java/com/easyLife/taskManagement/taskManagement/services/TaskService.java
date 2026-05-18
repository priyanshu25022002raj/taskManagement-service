package com.easyLife.taskManagement.taskManagement.services;

import com.easyLife.taskManagement.taskManagement.dto.TaskDto;
import com.easyLife.taskManagement.taskManagement.entities.Task;
import com.easyLife.taskManagement.taskManagement.entities.User;
import com.easyLife.taskManagement.taskManagement.repositories.TaskRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;


    public TaskDto create(@Valid TaskDto taskDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = modelMapper.map(taskDto, Task.class);
        task.setUser(user);
        Task task1 = taskRepository.save(task);
        return modelMapper.map(task1,TaskDto.class);
    }

    public List<TaskDto> getUserTask() {
        System.out.println("User role is: "+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> taskList = taskRepository.findByUser(user);
        return taskList.stream()
                .map((task) -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    public TaskDto getUserTaskByTaskId(Long taskId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepository.findById(taskId).orElseThrow(()->new RuntimeException("Task does not exist."));
        System.out.println("User id is: "+user.getId());
        System.out.println("task creation user id is"+task.getUser().getId());
        if(! user.getId().equals(task.getUser().getId())){
            throw new AccessDeniedException("You do not own this task.");
        }
        return modelMapper.map(task,TaskDto.class);
    }

    public TaskDto updateDueDateOfTaskByTaskId(Long taskId, LocalDate dueDate) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepository.findById(taskId).orElseThrow(()->new RuntimeException("Task does not exist."));
        System.out.println("User id is: "+user.getId());
        System.out.println("task creation user id is"+task.getUser().getId());
        if(! user.getId().equals(task.getUser().getId())){
            throw new AccessDeniedException("You do not own this task.");
        }
        task.setDueDate(dueDate);
        Task task1 = taskRepository.save(task);
        return modelMapper.map(task1, TaskDto.class);
    }

    public TaskDto updateTaskCompletedOfTaskByTaskId(Long taskId, boolean taskCompleted) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepository.findById(taskId).orElseThrow(()->new RuntimeException("Task does not exist."));
        System.out.println("User id is: "+user.getId());
        System.out.println("task creation user id is"+task.getUser().getId());
        if(! user.getId().equals(task.getUser().getId())){
            throw new AccessDeniedException("You do not own this task.");
        }
        task.setTaskCompleted(taskCompleted);
        Task task1 = taskRepository.save(task);
        return modelMapper.map(task1, TaskDto.class);
    }
}
