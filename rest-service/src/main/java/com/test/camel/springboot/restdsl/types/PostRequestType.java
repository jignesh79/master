package com.test.camel.springboot.restdsl.types;

/**
 * This is a POJO which defines a request format.
 * @author Jignesh
 *
 */
public class PostRequestType {

    String name;
	Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PostRequestType [name=" + name + ", quantity=" + quantity + "]";
	}    
    	
}
