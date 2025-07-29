package org.example.prometeus_grafana_monitoring.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.prometeus_grafana_monitoring.dto.ExceptionResponse;
import org.example.prometeus_grafana_monitoring.exception.CustomException;
import org.example.prometeus_grafana_monitoring.thirdparty.slack.SlackNotifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {

    private final SlackNotifier slackNotifier;

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(Exception ex, HttpServletRequest request) {
        System.out.println("handleRuntimeException called");
        ex.printStackTrace();

        String uri = request.getRequestURI();
        String params = request.getQueryString();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        sendSlackExceptionNoti(uri, params, time, ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), status.value(), status.name(), ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(Exception ex, HttpServletRequest request) {
        System.out.println("handleCustomException called");
        ex.printStackTrace();

        String uri = request.getRequestURI();
        String params = request.getQueryString();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        sendSlackExceptionNoti(uri, params, time, ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), status.value(), status.name(), ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    public void sendSlackExceptionNoti(String uri, String params, String time, Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("🚨 *예외 발생 알림* \n");
        sb.append("> ").append(time).append("\n");
        sb.append("> URL: ").append(uri);
        if (params != null) sb.append("?").append(params).append("\n");
        else sb.append("\n");
        sb.append("> 예외: ").append(ex.getClass().getSimpleName()).append("\n");
        sb.append("> 메시지: ").append(ex.getMessage());

        slackNotifier.send(sb.toString());
    }
}
