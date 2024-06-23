package com.pesto.task_management.repository;

import com.pesto.task_management.dto.TaskDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskDto, String> {
    TaskDto findByName(String name);
}
