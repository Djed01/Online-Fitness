package org.unibl.etf.model.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String name;
	private String surname;
	private String city;
	private String email;
	private Boolean activated;
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Integer id, String username, String name, String surname, String city, String email,
			Boolean activated) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.email = email;
		this.activated = activated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
