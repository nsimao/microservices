package com.nsimao.microservices.currencyconversionservice.proxy;

import com.nsimao.microservices.currencyconversionservice.command.ExchangeValueCommand;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Nelson Simão
 * @since 0.1
 */

@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    ExchangeValueCommand getExchangeValue(@PathVariable String from, @PathVariable String to);

}
