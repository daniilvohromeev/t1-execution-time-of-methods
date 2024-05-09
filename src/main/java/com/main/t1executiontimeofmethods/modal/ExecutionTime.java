package com.main.t1executiontimeofmethods.modal;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionTime extends BaseEntity<Long> {
    private String methodName;
    private long executionTime;
    private LocalDateTime timestamp;

}