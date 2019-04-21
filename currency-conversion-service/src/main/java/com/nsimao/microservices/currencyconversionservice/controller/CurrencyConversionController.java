package com.nsimao.microservices.currencyconversionservice.controller;

import com.nsimao.microservices.currencyconversionservice.command.CurrencyConversionCommand;
import com.nsimao.microservices.currencyconversionservice.command.ExchangeValueCommand;
import com.nsimao.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
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
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

// --------------------- GETTER / SETTER METHODS ---------------------

    @Inject
    public void setCurrencyExchangeServiceProxy(CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    @Inject
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

// -------------------------- OTHER METHODS --------------------------

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionCommand convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        ExchangeValueCommand exchangeValueCommand = currencyExchangeServiceProxy.getExchangeValue(from, to);
        CurrencyConversionCommand currencyConversionCommand = new CurrencyConversionCommand();
        currencyConversionCommand.setQuantity(quantity);
        currencyConversionCommand.setFrom(from);
        currencyConversionCommand.setTo(to);
        currencyConversionCommand.setPort(getPort());
        calculated(currencyConversionCommand, exchangeValueCommand, quantity);
        return currencyConversionCommand;
    }

    private Integer getPort() {
        return Integer.parseInt(environment.getProperty("local.server.port"));
    }

    private void calculated(CurrencyConversionCommand currencyConversionCommand, ExchangeValueCommand exchangeValueCommand, BigDecimal quantity) {
        if (exchangeValueCommand.getConversionMultiple() != null) {
            currencyConversionCommand.setConversionMultiple(exchangeValueCommand.getConversionMultiple());
            currencyConversionCommand.setTotalCalculatedAmount(exchangeValueCommand.getConversionMultiple().multiply(quantity));
        }
    }
}
