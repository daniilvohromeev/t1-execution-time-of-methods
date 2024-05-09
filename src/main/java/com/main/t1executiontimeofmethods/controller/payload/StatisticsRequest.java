package com.main.t1executiontimeofmethods.controller.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.main.t1executiontimeofmethods.annotation.ValidTimeRange;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ValidTimeRange
public class StatisticsRequest  {

    @NotNull
    @JsonProperty("method_name")
    @Schema(description = "Name of the method to retrieve statistics for", example = "getNumber")
    String methodName;

    @JsonProperty("time_start")
    @Schema(description = "Start time for the statistics period", type = "string", example = "2024-05-08T20:02:53.887234")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeStart;

    @JsonProperty("time_end")
    @Schema(description = "End time for the statistics period", type = "string", example = "2024-05-08T20:28:34.330826")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeEnd;
}
