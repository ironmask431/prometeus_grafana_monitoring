package org.example.prometeus_grafana_monitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;

    public ExceptionResponse(LocalDateTime timestamp, Integer status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
