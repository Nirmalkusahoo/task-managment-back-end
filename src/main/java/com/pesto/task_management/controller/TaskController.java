package com.pesto.task_management.controller;


import com.pesto.task_management.rto.TaskRto;
import com.pesto.task_management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:4301")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity addTask(@RequestBody TaskRto taskRto) {
        return taskService.addTask(taskRto);
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestBody TaskRto taskRto) {
        taskService.updateTask(taskRto);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/{name}")
    public ResponseEntity<TaskRto> getTask(@PathVariable @RequestBody String name) {
        return new ResponseEntity<>(taskService.getTask(name), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<TaskRto>> showAllQuestions() {
        List<TaskRto> list=taskService.getAllTasks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<List<TaskRto>> deleteTask(@PathVariable @RequestBody String name) {
        taskService.deleteTask(name);
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }
}
