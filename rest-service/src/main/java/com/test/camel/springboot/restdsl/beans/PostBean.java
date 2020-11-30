package com.test.camel.springboot.restdsl.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.test.camel.springboot.restdsl.types.ResponseType;

/**
 * This class creates response for the post request
 * @author Jignesh
 *
 */
@Component
public class PostBean {
	private static final Logger log = LoggerFactory.getLogger(PostBean.class);
	public ResponseType response(Order order) {
		return new ResponseType("Thanks for your order of " + order.getName() + "!");
	}

	public ResponseType invalid(String exception) {
		String message = "Invalid request- " + exception;
		log.debug("Invalid request- " + exception);
		return new ResponseType(message);
	}
}
