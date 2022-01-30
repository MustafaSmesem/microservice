package com.joumer.microservice.currencyexchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
//    @CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackMethod")
    public String sampleApi() throws Exception {
        log.info("api call");
        if (true) throw new Exception("exception");
        return "Sample API";
    }

    public String fallbackMethod(Exception ex) {
        log.warn(ex.getMessage());
        return "response";
    }

}
