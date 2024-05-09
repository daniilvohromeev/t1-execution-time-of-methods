package com.main.t1executiontimeofmethods.controller;

import com.main.t1executiontimeofmethods.controller.payload.StatisticsRequest;
import com.main.t1executiontimeofmethods.controller.payload.ResponseStatistics;
import com.main.t1executiontimeofmethods.service.ExecutionStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/metrics")
public class ExecMetricsResource implements BaseResource {

    private final ExecutionStatisticsService executionStatisticsService;

    public ExecMetricsResource(ExecutionStatisticsService executionStatisticsService) {
        this.executionStatisticsService = executionStatisticsService;
    }

    @PostMapping("/statistic")
    @Operation(summary = "Get statistics for a specific method",
            description = "Retrieve execution statistics for a single method over a specified time range.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of method statistics",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseStatistics.class)))
    public ResponseStatistics getStatistic(@Valid @RequestBody StatisticsRequest request) {
        return executionStatisticsService.getMethodStatistic(request.getMethodName(), request.getTimeStart(), request.getTimeEnd());
    }

    @GetMapping("/statistic/all")
    @Operation(summary = "Get all method statistics",
            description = "Retrieve execution statistics for all methods.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all method statistics",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ResponseStatistics.class))))
    public List<ResponseStatistics> getAllStatistics() {
        return executionStatisticsService.getAllMethodStatistics();
    }

    @GetMapping("/statistic/aggregate")
    @Operation(summary = "Get aggregated statistics",
            description = "Retrieve aggregated execution statistics across all methods.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of aggregated statistics",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseStatistics.class)))
    public ResponseStatistics getAggregatedStatistics() {
        return executionStatisticsService.getAggregatedStatistics();
    }

    @GetMapping("/statistic/grouped/date")
    @Operation(summary = "Get statistics grouped by date",
            description = "Retrieve execution statistics grouped by date for all methods.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of grouped statistics",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Map.class)))
    public Map<LocalDate, List<ResponseStatistics>> getStatisticsGroupedByDate() {
        return executionStatisticsService.getGroupedStatisticsByDate();
    }
}
