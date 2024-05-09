package com.main.t1executiontimeofmethods.controller.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatistics {
    @JsonProperty("method_name")
    private String methodName;

    @JsonProperty("min_time")
    private long minTime;

    @JsonProperty("max_time")
    private long maxTime;

    @JsonProperty("average_time")
    private double averageTime;

    @JsonProperty("standard_deviation")
    private double standardDeviation;
}