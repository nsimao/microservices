package com.nsimao.microservices.currencyconversionservice.command;

import java.math.BigDecimal;

/**
 * @author Nelson Simão
 * @since 0.1
 */

public class ExchangeValueCommand {
// ------------------------------ FIELDS ------------------------------

    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private Integer port;

// --------------------------- CONSTRUCTORS ---------------------------

    public ExchangeValueCommand() {

    }

    public ExchangeValueCommand(String from, String to, BigDecimal conversionMultiple) {
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
