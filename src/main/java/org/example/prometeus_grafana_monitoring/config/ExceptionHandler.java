package org.example.prometeus_grafana_monitoring.config;

import org.example.prometeus_grafana_monitoring.dto.ExceptionResponse;
import org.example.prometeus_grafana_monitoring.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(Exception ex) {
        System.out.println("handleRuntimeException called");
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), status.value(), status.name(), ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(Exception ex) {
        System.out.println("handleCustomException called");
        ex.printStackTrace();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), status.value(), status.name(), ex.getMessage());
        return new ResponseEntity<>(response, status);
    }
}
