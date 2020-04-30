package com.infy1.model;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

@Component
@Document(indexName= "library", type= "library")
public class Library {

	@Id
	private Integer id;
	private String name;
	private String address;
	
	public Library() {}
	
	public Library(int id,String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Library [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	
	
}
