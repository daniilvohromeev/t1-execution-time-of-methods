package com.main.t1executiontimeofmethods.modal.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatistics {
    private String methodName;
    private long minTime;
    private long maxTime;
    private double averageTime;
    private double standardDeviation;
}