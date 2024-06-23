package com.pesto.task_management;

import com.pesto.task_management.dto.TaskDto;
import com.pesto.task_management.repository.TaskRepository;
import com.pesto.task_management.rto.TaskRto;
import com.pesto.task_management.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTaskSuccessfully() {
        TaskRto taskRto = new TaskRto();
        taskRto.setName("Task1");
        taskRto.setDescription("Description1");
        taskRto.setStatus("Status1");

        when(taskRepository.save(any(TaskDto.class))).thenReturn(null);

        ResponseEntity<String> response = taskService.addTask(taskRto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Task added successfully", response.getBody());
    }

    @Test
    public void addTaskValidationFailed() {
        TaskRto taskRto = new TaskRto();
        taskRto.setName("Task1");
        taskRto.setDescription("Description1");

        when(taskRepository.save(any(TaskDto.class))).thenThrow(ConstraintViolationException.class);

        ResponseEntity<String> response = taskService.addTask(taskRto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation failed: Task should have status", response.getBody());
    }

    @Test
    public void getAllTasks() {
        TaskDto taskDto = new TaskDto();
        taskDto.setName("Task1");
        taskDto.setDescription("Description1");
        taskDto.setStatus("Status1");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(taskDto));

        List<TaskRto> tasks = taskService.getAllTasks();

        assertEquals(1, tasks.size());
        assertEquals("Task1", tasks.get(0).getName());
        assertEquals("Description1", tasks.get(0).getDescription());
        assertEquals("Status1", tasks.get(0).getStatus());
    }
}
