package com.example.ToDoList.repository;

import com.example.ToDoList.model.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDoTask,Long> {
    List<ToDoTask> findByTaskContaining(String keyword);
}
