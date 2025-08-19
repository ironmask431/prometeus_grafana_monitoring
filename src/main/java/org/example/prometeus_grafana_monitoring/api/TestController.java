package org.example.prometeus_grafana_monitoring.api;

import org.example.prometeus_grafana_monitoring.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test(){
        return "test";
    }

    @GetMapping("/slow-api-1")
    public String slowApi_1() throws InterruptedException {
        Random random = new Random();
        int randomNumber = random.nextInt(1001); // 0부터 1000까지 포함
        Thread.sleep(randomNumber);
        return "duration mills : "+randomNumber;
    }

    @GetMapping("/slow-api-2")
    public String slowApi_2() throws InterruptedException {
        Random random = new Random();
        int randomNumber = 1001 + random.nextInt(2000 - 1001 + 1); // 1001 ~ 2000 포함
        Thread.sleep(randomNumber);
        return "duration mills : "+randomNumber;
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
