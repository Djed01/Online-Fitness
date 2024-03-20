package org.unibl.etf.model.dto;

import java.io.Serializable;

public class AdminDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private boolean isLoggedIn;
	
	public AdminDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public AdminDTO(Integer id, String username, boolean isLoggedIn) {
		super();
		this.id = id;
		this.username = username;
		this.isLoggedIn = isLoggedIn;
	}
	
	
	
	
}
