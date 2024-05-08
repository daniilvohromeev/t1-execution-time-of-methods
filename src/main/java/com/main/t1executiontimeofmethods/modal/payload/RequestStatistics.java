package com.main.t1executiontimeofmethods.modal.payload;

import com.main.t1executiontimeofmethods.annotation.ValidTimeRange;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ValidTimeRange
public class RequestStatistics {
    String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeEnd;
}
