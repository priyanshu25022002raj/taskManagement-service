package com.easyLife.taskManagement.taskManagement.repositories;

import com.easyLife.taskManagement.taskManagement.entities.Task;
import com.easyLife.taskManagement.taskManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUser(User user);
}
