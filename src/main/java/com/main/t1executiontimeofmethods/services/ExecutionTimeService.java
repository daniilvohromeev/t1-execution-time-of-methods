package com.main.t1executiontimeofmethods.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ExecutionTimeService {

    @Async
    public void asyncSaveExecutionTime(String methodName, long executionTime) {
        // Реализация сохранения данных в базе данных
    }

    public void saveExecutionTime(String methodName, long executionTime) {
        // Реализация сохранения данных в базе данных
    }

}
