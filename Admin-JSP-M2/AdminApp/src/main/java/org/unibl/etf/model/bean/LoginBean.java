package org.unibl.etf.model.bean;

import java.sql.SQLException;

import org.unibl.etf.dao.LoginDAO;
import org.unibl.etf.dao.impl.LoginDAOImpl;
import org.unibl.etf.model.dto.AdminDTO;

public class LoginBean {
	
	private LoginDAO loginDAO;
	
	public LoginBean() {
		this.loginDAO = new LoginDAOImpl();
	}
	
	public AdminDTO login(String username,String password) {
		try {
			return loginDAO.login(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			AdminDTO adminDTO = new AdminDTO();
			adminDTO.setLoggedIn(false);
			return adminDTO;
		}
	}
}
