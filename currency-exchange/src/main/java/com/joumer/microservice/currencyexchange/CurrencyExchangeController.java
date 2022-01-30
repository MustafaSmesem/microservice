package com.joumer.microservice.currencyexchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final String hello;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository, @Value("${test.hello}") String hello) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
        this.hello = hello;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ResponseEntity<?> getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        System.out.println(hello);

        var port = environment.getProperty("local.server.port");
        var currencyExchangeOpt = currencyExchangeRepository.findFirstByFromAndTo(from.toUpperCase(Locale.ROOT), to.toUpperCase(Locale.ROOT));
        if (currencyExchangeOpt.isEmpty())
            return ResponseEntity.notFound().build();
        var currencyExchange = currencyExchangeOpt.get();
        currencyExchange.setEnvironment(String.format("Currency Exchange Service port[%s]", port));
        return ResponseEntity.ok(currencyExchange);
    }

}
