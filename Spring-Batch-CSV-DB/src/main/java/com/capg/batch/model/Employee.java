package com.capg.batch.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	private int id;
	private String name;
	private int price;
	private Date time;
	
	public Employee() {
		super();
	}

	public Employee(int id, String name, int price, Date time) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", price=" + price + ", time=" + time + "]";
	}
	
	
}
