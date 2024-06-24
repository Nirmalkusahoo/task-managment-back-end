package com.pesto.task_management.repository;

import com.pesto.task_management.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
    Optional<UserDto> findByUserName(String username);
}
