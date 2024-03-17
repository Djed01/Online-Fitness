package org.unibl.etf.model.dto;

import java.io.Serializable;

public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
