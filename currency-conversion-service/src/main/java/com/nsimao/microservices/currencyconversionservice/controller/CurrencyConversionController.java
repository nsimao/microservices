package com.nsimao.microservices.currencyconversionservice.controller;

import com.nsimao.microservices.currencyconversionservice.command.CurrencyConversionCommand;
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
public class CurrencyConversionController {
// ------------------------------ FIELDS ------------------------------

    private Environment environment;

// --------------------- GETTER / SETTER METHODS ---------------------

    @Inject
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

// -------------------------- OTHER METHODS --------------------------

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionCommand convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        BigDecimal conversionMultiple = new BigDecimal(10);
        BigDecimal returnedValue = conversionMultiple.multiply(quantity);
        CurrencyConversionCommand currencyConversionCommand = new CurrencyConversionCommand(from, to, conversionMultiple, quantity, returnedValue);
        currencyConversionCommand.setPort(getPort());
        return currencyConversionCommand;
    }

    private Integer getPort() {
        return Integer.parseInt(environment.getProperty("local.server.port"));
    }
}
