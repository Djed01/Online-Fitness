package org.unibl.etf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.dao.LoginDAO;
import org.unibl.etf.model.dto.AdminDTO;

public class LoginDAOImpl implements LoginDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ADMIN = "SELECT * FROM admin WHERE username=?";
	
	@Override
	public AdminDTO login(String username,String password) throws SQLException{
		Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet resultSet = null;
        
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(SELECT_ADMIN);
            pstmt.setString(1, username);
            resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                String passwordFromDB = resultSet.getString("passwordHash");
                if (passwordFromDB.equals(password)) {
                	AdminDTO adminDTO = new AdminDTO();
                	adminDTO.setId(resultSet.getInt("id"));
                	adminDTO.setUsername(resultSet.getString("username"));
                	adminDTO.setLoggedIn(true);
                	return adminDTO;
                } else {
                	AdminDTO adminDTO = new AdminDTO();
                	adminDTO.setLoggedIn(false);
                    return adminDTO; // Passwords don't match
                }
            } else {
            	AdminDTO adminDTO = new AdminDTO();
            	adminDTO.setLoggedIn(false);
                return adminDTO; // No admin with the provided username
            }
        } finally {
        	connectionPool.checkIn(connection);
        }
	}
}
