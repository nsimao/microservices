package com.nsimao.microservices.limitsservice.command;

/**
 * @author Nelson Simão
 * @since 0.1
 */

public class LimitConfigurationCommand {
// ------------------------------ FIELDS ------------------------------

    private int maximum;
    private int minimum;

// --------------------------- CONSTRUCTORS ---------------------------

    public LimitConfigurationCommand() {
    }

    public LimitConfigurationCommand(int maximum, int minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

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
