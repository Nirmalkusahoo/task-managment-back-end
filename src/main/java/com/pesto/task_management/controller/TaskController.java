package com.pesto.task_management.controller;


import com.pesto.task_management.rto.ResponseMessage;
import com.pesto.task_management.rto.TaskRto;
import com.pesto.task_management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TaskController is a REST controller that handles task related requests.
 * It exposes endpoints for adding, updating, retrieving and deleting tasks.
 * It uses TaskService to perform the actual operations.
 */
@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:4300")
public class TaskController {
    @Autowired
    TaskService taskService;

    /**
     * This method handles task creation requests.
     * It receives a TaskRto object as request body which contains task details.
     * It uses TaskService to perform the task creation operation.
     * @param taskRto - Object containing task details.
     * @return ResponseEntity with ResponseMessage and HTTP status.
     */
    @PostMapping
    public ResponseEntity<ResponseMessage> addTask(@RequestBody TaskRto taskRto) {
        return taskService.addTask(taskRto);
    }

    /**
     * This method handles task update requests.
     * It receives a TaskRto object as request body which contains updated task details.
     * It uses TaskService to perform the task update operation.
     * @param taskRto - Object containing updated task details.
     * @return ResponseEntity with HTTP status.
     */
    @PutMapping
    public ResponseEntity updateTask(@RequestBody TaskRto taskRto) {
        taskService.updateTask(taskRto);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * This method handles task retrieval requests.
     * It receives a task name as path variable.
     * It uses TaskService to retrieve the task details.
     * @param name - Name of the task to be retrieved.
     * @return ResponseEntity with TaskRto object containing task details and HTTP status.
     */
    @GetMapping("/{name}")
    public ResponseEntity<TaskRto> getTask(@PathVariable @RequestBody String name) {
        return new ResponseEntity<>(taskService.getTask(name), HttpStatus.OK);
    }

    /**
     * This method handles requests to retrieve all tasks.
     * It uses TaskService to retrieve all tasks.
     * @return ResponseEntity with a list of TaskRto objects containing details of all tasks and HTTP status.
     */
    @GetMapping("/all")
    public ResponseEntity<List<TaskRto>> showAllQuestions() {
        List<TaskRto> list=taskService.getAllTasks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * This method handles task deletion requests.
     * It receives a task name as path variable.
     * It uses TaskService to delete the task.
     * After deletion, it retrieves the updated list of all tasks.
     * @param name - Name of the task to be deleted.
     * @return ResponseEntity with a list of TaskRto objects containing details of all remaining tasks and HTTP status.
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<List<TaskRto>> deleteTask(@PathVariable @RequestBody String name) {
        taskService.deleteTask(name);
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }
}
