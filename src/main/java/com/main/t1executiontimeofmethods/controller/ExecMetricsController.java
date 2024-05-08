package com.main.t1executiontimeofmethods.controller;

import com.main.t1executiontimeofmethods.modal.payload.RequestStatistics;
import com.main.t1executiontimeofmethods.modal.payload.ResponseStatistics;
import com.main.t1executiontimeofmethods.service.ExecutionStatisticsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/metrics")
public class ExecMetricsController{

    private final ExecutionStatisticsService executionStatisticsService;

    public ExecMetricsController(ExecutionStatisticsService executionStatisticsService) {
        this.executionStatisticsService = executionStatisticsService;
    }

    @GetMapping("/statistic")
    public ResponseStatistics getStatistic(@Valid @RequestBody RequestStatistics request){
       return executionStatisticsService.getMethodStatistic(request.getName(), request.getTimeStart(), request.getTimeEnd());
    }
}
