package com.nsimao.microservices.currencyexchangeservice.repository;

import com.nsimao.microservices.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Nelson Simão
 * @since 0.1
 */

public interface ExchangeValueRepository extends CrudRepository<ExchangeValue, Long> {
// -------------------------- OTHER METHODS --------------------------

    ExchangeValue findByFromAndTo(String from, String to);
}
