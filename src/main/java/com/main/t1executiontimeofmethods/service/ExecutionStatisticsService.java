package com.main.t1executiontimeofmethods.service;

import com.main.t1executiontimeofmethods.exception.DataNotFoundException;
import com.main.t1executiontimeofmethods.modal.ExecutionTime;
import com.main.t1executiontimeofmethods.modal.payload.ResponseStatistics;
import com.main.t1executiontimeofmethods.repository.ExecutionTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ExecutionStatisticsService {
    private final ExecutionTimeRepository repository;

    public ExecutionStatisticsService(ExecutionTimeRepository repository) {
        this.repository = repository;
    }

    public ResponseStatistics getMethodStatistic(String methodName, LocalDateTime start, LocalDateTime end) {
        List<ExecutionTime> executions = repository.findAllByMethodNameAndTimestampBetween(methodName, start, end)
                .orElseThrow(() -> new DataNotFoundException("No execution data available for the specified time range and method."));

        LongSummaryStatistics stats = executions.stream()
                .mapToLong(ExecutionTime::getExecutionTime)
                .summaryStatistics();

        long min = stats.getMin();
        long max = stats.getMax();
        double average = stats.getAverage();

        // Вычисление стандартного отклонения
        double variance = executions.stream()
                .mapToDouble(execution -> Math.pow(execution.getExecutionTime() - average, 2))
                .average()
                .orElse(0.0); // Значение по умолчанию в случае пустого стрима
        double standardDeviation = Math.sqrt(variance);

        return new ResponseStatistics(methodName, min, max, average, standardDeviation);
    }
}