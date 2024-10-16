package com.example.ToDoList.service;

import com.example.ToDoList.model.ToDoTask;
import com.example.ToDoList.repository.ToDoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository repository;

    //INSERT TASK
    public void saveTask(ToDoTask task) {
        if (task.getCreated_date() == null) {
            task.setCreated_date(new Date());
        }
        if (task.getStatus() == null || task.getStatus().isEmpty()) {
            task.setStatus("Incomplete");
        }
        repository.save(task);
    }

    //DELETE TASK
    public boolean deleteTask(Long id) {
        Optional<ToDoTask> task = repository.findById(id);
        if (task.isPresent()) {
            repository.delete(task.get());
            return true;
        }
        return false;
    }

    //UPDATES STATUS
    public void updateStatus(Long id) {
        ToDoTask existingTask = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setStatus("Completed");
        repository.save(existingTask);
    }


    //FETCH ALL TASKS
    public List<ToDoTask> getAllTasks() {
        List<ToDoTask> result = repository.findAll();
        return repository.findAll();
    }

    //FETCH TASK BY ID
    public Optional<ToDoTask> getTaskById(Long id) {
        return repository.findById(id);
    }

    //FETCH TASK BY KEYWORD
    public List<ToDoTask> getTaskByKeyword(String keyword) {
        return repository.findByTaskContaining(keyword);
    }
}
