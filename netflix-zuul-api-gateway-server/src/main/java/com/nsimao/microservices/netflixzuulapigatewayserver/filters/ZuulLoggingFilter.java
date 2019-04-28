package com.nsimao.microservices.netflixzuulapigatewayserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Nelson Simão
 * @since 0.1
 */

@Component
public class ZuulLoggingFilter extends ZuulFilter {
// ------------------------------ FIELDS ------------------------------

    private Logger logger = LoggerFactory.getLogger(this.getClass());

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface IZuulFilter ---------------------

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request =
                RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri -> {}",
                request, request.getRequestURI());
        return null;
    }

// -------------------------- OTHER METHODS --------------------------

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return "pre";
    }
}