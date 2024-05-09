package com.main.t1executiontimeofmethods.service;

import com.main.t1executiontimeofmethods.exception.DataNotFoundException;
import com.main.t1executiontimeofmethods.modal.ExecutionTime;
import com.main.t1executiontimeofmethods.controller.payload.ResponseStatistics;
import com.main.t1executiontimeofmethods.repository.ExecutionTimeRepository;
import com.main.t1executiontimeofmethods.utils.StatisticsCalculator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Order(1)
public class ExecutionStatisticsService {
    private final ExecutionTimeRepository repository;

    private final StatisticsCalculator statisticsCalculator;

    public ExecutionStatisticsService(ExecutionTimeRepository repository, StatisticsCalculator statisticsCalculator) {
        this.repository = repository;
        this.statisticsCalculator = statisticsCalculator;
    }

    public ResponseStatistics getMethodStatistic(String methodName, LocalDateTime start, LocalDateTime end) {
        List<ExecutionTime> executions = repository.findAllByMethodNameAndTimestampBetween(methodName, start, end);
        if (executions.isEmpty()) {
            throw new DataNotFoundException("No data found for the specified method and time range.");
        }
        return statisticsCalculator.calculateStatistics(methodName, executions);
    }

    public List<ResponseStatistics> getAllMethodStatistics() {
        List<ExecutionTime> allExecutions = repository.findAll();
        if (allExecutions.isEmpty()) {
            throw new DataNotFoundException("No data found");
        }
        return allExecutions.stream()
                .filter(executionTime -> Objects.nonNull(executionTime.getMethodName()))
                .collect(Collectors.groupingBy(ExecutionTime::getMethodName))
                .entrySet().stream()
                .map(entry -> statisticsCalculator.calculateStatistics(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public ResponseStatistics getAggregatedStatistics() {
        List<ExecutionTime> allExecutions = repository.findAll();
        if (allExecutions.isEmpty()) {
            throw new DataNotFoundException("No data found");
        }
        return statisticsCalculator.calculateStatistics("All Methods Aggregate", allExecutions);
    }

    public Map<LocalDate, List<ResponseStatistics>> getGroupedStatisticsByDate() {
        List<ExecutionTime> allExecutions = repository.findAll();
        if (allExecutions.isEmpty()) {
            throw new DataNotFoundException("No data found");
        }
        return allExecutions.stream()
                .collect(Collectors.groupingBy(execution -> execution.getTimestamp().toLocalDate()))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream()
                        .collect(Collectors.groupingBy(ExecutionTime::getMethodName))
                        .entrySet().stream()
                        .map(e -> statisticsCalculator.calculateStatistics(e.getKey(), e.getValue()))
                        .collect(Collectors.toList())));
    }
}