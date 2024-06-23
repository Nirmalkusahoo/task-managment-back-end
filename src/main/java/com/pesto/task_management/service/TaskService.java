package com.pesto.task_management.service;


import com.pesto.task_management.dto.TaskDto;
import com.pesto.task_management.repository.TaskRepository;
import com.pesto.task_management.rto.TaskRto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;


    public ResponseEntity<String> addTask(@Valid TaskRto taskRto) {
        try {
            TaskDto taskDto = mapTaskRtoToDto(taskRto);
            taskRepository.save(taskDto);
            return new ResponseEntity<>("Task added successfully", HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>("Validation failed: " + "Task should have status", HttpStatus.BAD_REQUEST);
        }

    }

    public void updateTask(TaskRto taskRto) {
        TaskDto taskDto = mapTaskRtoToDto(taskRto);
        taskRepository.save(taskDto);
    }


    public TaskRto getTask(String name) {
        TaskDto taskDto = taskRepository.findByName(name);
        TaskRto taskRto = mapDtoToRto(taskDto);
        return taskRto;
    }

    public List<TaskRto> getAllTasks() {
        List<TaskDto> list = taskRepository.findAll();
        return list.stream().map(this::mapDtoToRto).collect(Collectors.toList());
    }

    public void deleteTask(String name) {
        taskRepository.deleteById(name);
    }

    private TaskRto mapDtoToRto(TaskDto taskDto) {
        TaskRto rto = new TaskRto();
        rto.setName(taskDto.getName());
        rto.setDescription(taskDto.getDescription());
        rto.setStatus(taskDto.getStatus());
        return rto;
    }

    private TaskDto mapTaskRtoToDto(@Valid TaskRto taskRto) throws ConstraintViolationException {
        TaskDto taskDto = new TaskDto();
        try {
            taskDto.setName(taskRto.getName());
            taskDto.setDescription(taskRto.getDescription());
            taskDto.setStatus(taskRto.getStatus());
            return taskDto;
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Task should have status", null);
        }

    }
}
