package com.nsimao.microservices.limitsservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nelson Simão
 * @since 0.1
 */

@Component
@ConfigurationProperties("limits-service")
public class Configuration {
// ------------------------------ FIELDS ------------------------------

    private int minimum;
    private int maximum;

// --------------------- GETTER / SETTER METHODS ---------------------

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
}
