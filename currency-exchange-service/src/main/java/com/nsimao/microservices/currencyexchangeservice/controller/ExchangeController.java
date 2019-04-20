package com.nsimao.microservices.currencyexchangeservice.controller;

import com.nsimao.microservices.currencyexchangeservice.command.ExchangeValueCommand;
import com.nsimao.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author Nelson Simão
 * @since 0.1
 */

@RestController
public class ExchangeController {
// ------------------------------ FIELDS ------------------------------

    private Environment environment;
    private ExchangeValueRepository exchangeValueRepository;

// --------------------- GETTER / SETTER METHODS ---------------------

    @Inject
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Inject
    public void setExchangeValueRepository(ExchangeValueRepository exchangeValueRepository) {
        this.exchangeValueRepository = exchangeValueRepository;
    }

// -------------------------- OTHER METHODS --------------------------

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValueCommand getExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValueCommand exchangeValueCommand = ExchangeValueCommand.mapFrom(exchangeValueRepository.findByFromAndTo(from, to));
        exchangeValueCommand.setPort(getPort());
        return exchangeValueCommand;
    }

    private Integer getPort() {
        return Integer.parseInt(environment.getProperty("local.server.port"));
    }
}
