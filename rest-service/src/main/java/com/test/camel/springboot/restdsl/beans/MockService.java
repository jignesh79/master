package com.test.camel.springboot.restdsl.beans;

import org.springframework.stereotype.Component;

/**
 * This class is mock Service to receive a message and return some sample response
 * @author Jignesh
 *
 */
@Component
public class MockService {

    public Order update(Order order) {
    	double price = 203d; // get its price
    	order.setPrice(price);
    	return order;
    }
}
