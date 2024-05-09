package com.main.t1executiontimeofmethods.utils;

import com.main.t1executiontimeofmethods.controller.payload.ResponseStatistics;
import com.main.t1executiontimeofmethods.modal.ExecutionTime;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.LongSummaryStatistics;

@Component
public class StatisticsCalculator {
    public ResponseStatistics calculateStatistics(String methodName, List<ExecutionTime> executions) {
        LongSummaryStatistics stats = executions.stream()
                .mapToLong(ExecutionTime::getExecutionTime)
                .summaryStatistics();

        double standardDeviation = Math.sqrt(executions.stream()
                .mapToDouble(execution -> Math.pow(execution.getExecutionTime() - stats.getAverage(), 2))
                .average().orElse(0.0));

        return new ResponseStatistics(
                methodName,
                stats.getMin(),
                stats.getMax(),
                stats.getAverage(),
                standardDeviation
        );
    }
}