package org.unibl.etf.model.dto;

import java.io.Serializable;

public class AttributeDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer categoryId;
	
	public AttributeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttributeDTO(Integer id, String name, Integer categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
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
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}
