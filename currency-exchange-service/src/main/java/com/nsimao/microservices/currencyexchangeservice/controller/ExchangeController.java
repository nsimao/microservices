package com.nsimao.microservices.currencyexchangeservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nsimao.microservices.currencyexchangeservice.command.ExchangeValueCommand;
import com.nsimao.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * @author Nelson Simão
 * @since 0.1
 */

@RestController
public class ExchangeController {
// ------------------------------ FIELDS ------------------------------

    private Environment environment;
    private ExchangeValueRepository exchangeValueRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
    @HystrixCommand(fallbackMethod = "getDefaultExchangeValue")
    public ExchangeValueCommand getExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValueCommand exchangeValueCommand = ExchangeValueCommand.mapFrom(exchangeValueRepository.findByFromAndTo(from, to));
        exchangeValueCommand.setPort(getPort());

        logger.info("Currency exchange service response: {}", exchangeValueCommand);

        return exchangeValueCommand;
    }

    private ExchangeValueCommand getDefaultExchangeValue(String from, String to, Throwable t) {
        logger.error("FallBack getDefaultExchangeValue method executed, exception: {}", t.getMessage(), t);

        ExchangeValueCommand command = new ExchangeValueCommand();
        command.setFrom(from);
        command.setTo(to);
        command.setConversionMultiple(new BigDecimal("0.0"));
        command.setPort(getPort());
        return command;
    }

    private Integer getPort() {
        return Integer.parseInt(environment.getProperty("local.server.port"));
    }
}
