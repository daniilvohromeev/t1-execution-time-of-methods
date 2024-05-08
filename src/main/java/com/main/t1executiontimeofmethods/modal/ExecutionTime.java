package com.main.t1executiontimeofmethods.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String methodName;
    private long executionTime;
    private LocalDateTime timestamp;
}