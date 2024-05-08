package com.main.t1executiontimeofmethods.repository;

import com.main.t1executiontimeofmethods.modal.ExecutionTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {

    Optional<List<ExecutionTime>> findAllByMethodNameAndTimestampBetween(String methodName, LocalDateTime start, LocalDateTime end);

}
