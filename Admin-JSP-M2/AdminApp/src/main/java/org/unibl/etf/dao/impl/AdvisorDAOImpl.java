package org.unibl.etf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.AdvisorDAO;
import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.dao.DAOUtil;
import org.unibl.etf.model.dto.AdvisorDTO;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class AdvisorDAOImpl implements AdvisorDAO{
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SELECT_ALL_ADVISORS_QUERY = "SELECT * FROM advisor WHERE status=true";
	private static final String INSERT_ADVISOR_QUERY = "INSERT INTO advisor (username,passwordHash,name,surname,email,status) VALUES (?,?,?,?,?,?)";
	private static final String DELETE_ADVISOR_QUERY = "UPDATE advisor SET status=? WHERE id = ?";
	private static final String UPDATE_ADVISOR_QUERY = "UPDATE advisor SET name = ?, surname = ?, username = ?, email = ? WHERE id = ?";
	
	public AdvisorDAOImpl() {
		
	}
	
	@Override
	public List<AdvisorDTO> getAllAdvisors() throws SQLException{
		List<AdvisorDTO> advisors = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        Object values[] = {};
        try {
        	connection = connectionPool.checkOut();
        	
        	PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ALL_ADVISORS_QUERY, false, values);
        	resultSet = pstmt.executeQuery();	
        	 
            while (resultSet.next()) {
                AdvisorDTO advisor = new AdvisorDTO();
                advisor.setId(resultSet.getInt("id"));
                advisor.setUsername(resultSet.getString("username"));
                advisor.setName(resultSet.getString("name"));
                advisor.setSurname(resultSet.getString("surname"));
                advisor.setEmail(resultSet.getString("email"));
                advisor.setStatus(resultSet.getBoolean("status"));
                advisors.add(advisor);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
	    } finally {
			connectionPool.checkIn(connection);
		}
        return advisors;
	}
	
	@Override
	public boolean deleteAdvisor(Integer id) throws SQLException{
		Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(DELETE_ADVISOR_QUERY);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return false;
	}
	
	@Override
	public boolean updateAdvisor(AdvisorDTO advisor) throws SQLException{
		Connection connection = null;
	    PreparedStatement pstmt = null;
	    try {
	        connection = connectionPool.checkOut();
	        pstmt = connection.prepareStatement(UPDATE_ADVISOR_QUERY);
	        pstmt.setString(1, advisor.getName());
	        pstmt.setString(2, advisor.getSurname());
	        pstmt.setString(3, advisor.getUsername());
	        pstmt.setString(4, advisor.getEmail());
	        pstmt.setInt(5, advisor.getId());
	        int rowsUpdated = pstmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        connectionPool.checkIn(connection);
	    }
	    return false;
	}
	
	@Override
	public boolean insertAdvisor(AdvisorDTO advisor) throws SQLException{
		Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(INSERT_ADVISOR_QUERY);
            
            pstmt.setString(1,advisor.getUsername());
            String hashedPassword = BCrypt.withDefaults().hashToString(12, advisor.getPassword().toCharArray());
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3,advisor.getName());
            pstmt.setString(4,advisor.getSurname());
            pstmt.setString(5,advisor.getEmail());
            pstmt.setBoolean(6,true);
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return false;
	}
}
