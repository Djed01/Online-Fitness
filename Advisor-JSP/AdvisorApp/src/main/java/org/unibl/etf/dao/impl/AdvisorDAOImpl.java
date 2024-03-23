package org.unibl.etf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.unibl.etf.dao.AdvisorDAO;
import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.model.dto.AdvisorDTO;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class AdvisorDAOImpl implements AdvisorDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ADVISOR = "SELECT * FROM advisor WHERE username=?";
	
	@Override
	public AdvisorDTO login(String username,String password) throws SQLException{
		Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet resultSet = null;
        
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(SELECT_ADVISOR);
            pstmt.setString(1, username);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String passwordFromDB = resultSet.getString("passwordHash");
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), passwordFromDB);
                if (result.verified == true) {
                	AdvisorDTO advisorDTO = new AdvisorDTO();
                	advisorDTO.setId(resultSet.getInt("id"));
                	advisorDTO.setUsername(resultSet.getString("username"));
                	advisorDTO.setEmail(resultSet.getString("email"));
                	advisorDTO.setIsLoggedIn(true);
                	return advisorDTO;
                } else {
                	AdvisorDTO advisorDTO = new AdvisorDTO();
                	advisorDTO.setIsLoggedIn(false);
                    return advisorDTO; // Passwords don't match
                }
            } else {
            	AdvisorDTO advisorDTO = new AdvisorDTO();
            	advisorDTO.setIsLoggedIn(false);
                return advisorDTO; // No admin with the provided username
            }
        } finally {
        	connectionPool.checkIn(connection);
        }
	}
}
