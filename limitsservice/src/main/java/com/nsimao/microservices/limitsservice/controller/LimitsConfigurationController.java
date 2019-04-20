package com.nsimao.microservices.limitsservice.controller;

import com.nsimao.microservices.limitsservice.command.LimitConfigurationCommand;
import com.nsimao.microservices.limitsservice.configuration.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author Nelson Simão
 * @since 0.1
 */

@RestController
public class LimitsConfigurationController {
// ------------------------------ FIELDS ------------------------------

    private Configuration configuration;

// --------------------- GETTER / SETTER METHODS ---------------------

    @Inject
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

// -------------------------- OTHER METHODS --------------------------

    @GetMapping("/limits")
    public LimitConfigurationCommand retrieveLimitConfiguration() {
        return new LimitConfigurationCommand(configuration.getMaximum(), configuration.getMinimum());
    }
}
