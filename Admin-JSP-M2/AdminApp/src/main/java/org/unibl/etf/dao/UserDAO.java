package org.unibl.etf.dao;

import java.sql.SQLException;
import java.util.List;

import org.unibl.etf.model.dto.UserDTO;

public interface UserDAO {
	
	List<UserDTO> getAllUsers() throws SQLException;
	boolean delteUser(Integer userId) throws SQLException;
	boolean updateUser(UserDTO userDTO) throws SQLException;
}
