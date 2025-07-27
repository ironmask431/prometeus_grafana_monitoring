package org.example.prometeus_grafana_monitoring.api;

import org.example.prometeus_grafana_monitoring.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test(){
        return "test";
    }

    @GetMapping("/runtime-excpetion")
    public String runtimeException(){
        throw new RuntimeException("런타임 예외 발생");
    }

    @GetMapping("/custom-excpetion")
    public String customException(){
        throw new CustomException("커스텀 예외 발생");
    }

}
