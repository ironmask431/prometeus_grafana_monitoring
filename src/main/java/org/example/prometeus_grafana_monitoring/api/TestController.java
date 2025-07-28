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

    @GetMapping("/slow-5")
    public String slowApi5() throws InterruptedException {
        Thread.sleep(5000);
        return "slow-5-second";
    }

    @GetMapping("/slow-10")
    public String slowApi10() throws InterruptedException {
        Thread.sleep(10000);
        return "slow-10-second";
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
