package com.test.camel.springboot.restdsl.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.camel.springboot.restdsl.types.PostRequestType;

/**
 * This class transform a message from front end to back end format
 * @author Jignesh
 *
 */
@Component
public class Transformer {
	private static final Logger log = LoggerFactory.getLogger(Transformer.class);
    public Order response(PostRequestType request) {
        Order order = new Order(request.getName(), request.getQuantity());
        log.debug("Transforming request " + request +" to Order " + order);  
    	return order;
    }
}
