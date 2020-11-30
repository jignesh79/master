package com.test.camel.springboot.restdsl.types;

/**
 * This is a POJO which defines a response format.
 * @author Jignesh
 *
 */
public class ResponseType {

    private String message;

    public ResponseType() {
    }

    public ResponseType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "ResponseType [message=" + message + "]";
	}
    
    
}
