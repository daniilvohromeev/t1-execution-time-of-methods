package com.main.t1executiontimeofmethods.service;

import com.main.t1executiontimeofmethods.modal.ExecutionTime;
import com.main.t1executiontimeofmethods.repository.ExecutionTimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class ExecutionTimeSaveService {

    private final ExecutionTimeRepository executionTimeRepository;

    public ExecutionTimeSaveService(ExecutionTimeRepository executionTimeRepository) {
        this.executionTimeRepository = executionTimeRepository;
    }

    @Async
    public void asyncSaveExecutionTime(String methodName, long executionTime, LocalDateTime timestamp) {
        ExecutionTime data = ExecutionTime
                .builder()
                .methodName(methodName)
                .executionTime(executionTime)
                .timestamp(timestamp)
                .build();
         executionTimeRepository.save(data);
    }

}
