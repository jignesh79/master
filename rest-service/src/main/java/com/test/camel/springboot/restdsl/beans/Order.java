package com.test.camel.springboot.restdsl.beans;

import org.springframework.stereotype.Component;

/**
 * Back end schema format, order class with price
 * @author Jignesh
 *
 */
@Component
public class Order {

	private String name;
	int quantity;
	double price;

	
	public Order() {
		super();
	}

	public Order(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	public Order(String name, int quantity, double price) {
		this(name, quantity);
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
