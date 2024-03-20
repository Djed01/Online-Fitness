package org.unibl.etf.model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.UserDAO;
import org.unibl.etf.dao.impl.UserDAOImpl;
import org.unibl.etf.model.dto.UserDTO;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	public UserBean() {
		this.userDAO = new UserDAOImpl();
	}
	
	public List<UserDTO> getAllUsers(){
		List<UserDTO> users = new ArrayList<UserDTO>();
		try {
			users = this.userDAO.getAllUsers();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean deleteUser(Integer userId) {
		try {
			return this.userDAO.delteUser(userId);
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateUser(UserDTO user) {
		try {
			return this.userDAO.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
