package com.test.camel.springboot.restdsl.beans;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * This class will handle the validate exception and set its message in the body
 * @author Jignesh
 *
 */
@Component
public class FailureProcessor implements Processor {
	private static final Logger log = LoggerFactory.getLogger(FailureProcessor.class);
	
	public void process(Exchange exchange) throws Exception {
		Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.BAD_REQUEST.value());
		String failure = "The request failed because " + e.getMessage();
		log.debug("failure- " + failure);
		exchange.getIn().setBody( failure);
	}
}
