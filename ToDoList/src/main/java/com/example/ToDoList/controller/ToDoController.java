package com.example.ToDoList.controller;

import com.example.ToDoList.model.ToDoTask;
import com.example.ToDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
public class ToDoController {

    @Autowired
    ToDoService service;



    @GetMapping("/viewToDoTask")
    public String viewToDoTask(Model model) {
        List<ToDoTask> tasks = service.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "ViewToDoTask";
    }

    @GetMapping("/getTaskByID")
    public String getTaskById(@RequestParam Long id, Model model) {
        Optional<ToDoTask> task = service.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
        } else {
            model.addAttribute("error", "Task not found");
        }
        return "ViewToDoTask";
    }

    @GetMapping("/addTaskForm")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new ToDoTask());
        return "AddToDoTask";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute ToDoTask task) {
        service.saveTask(task);
        return "redirect:/viewToDoTask";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam Long id) {
        service.updateStatus(id);
        return "redirect:/viewToDoTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute ToDoTask task) {
        service.saveTask(task);
        return "redirect:/viewToDoTask";
    }

    @GetMapping("/editTask")
    public String editTask(@RequestParam Long id, Model model) {
        Optional<ToDoTask> task = service.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "EditToDoTask";
        } else {
            model.addAttribute("error", "Task not found");
            return "redirect:/viewToDoTask";
        }
    }

    @PostMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id, Model model) {
        boolean isDeleted = service.deleteTask(id);
        return "redirect:/viewToDoTask";
    }
}
