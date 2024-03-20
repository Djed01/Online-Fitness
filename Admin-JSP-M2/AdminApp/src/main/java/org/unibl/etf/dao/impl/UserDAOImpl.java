package org.unibl.etf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.dao.DAOUtil;
import org.unibl.etf.dao.UserDAO;
import org.unibl.etf.model.dto.UserDTO;

public class UserDAOImpl implements UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM user WHERE status=true";
    private static final String DELETE_USER_QUERY = "UPDATE user SET status=? WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET name = ?, surname = ?, username = ?, city = ?, email = ?, activationStatus = ? WHERE id = ?";
    
    public UserDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	@Override
    public List<UserDTO> getAllUsers() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        Object values[] = {};
        try {
        	connection = connectionPool.checkOut();
        	
        	PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ALL_USERS_QUERY, false, values);
        	resultSet = pstmt.executeQuery();	
        	 
            while (resultSet.next()) {
                UserDTO user = new UserDTO();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setCity(resultSet.getString("city"));
                user.setEmail(resultSet.getString("email"));
                user.setActivated(resultSet.getBoolean("activationStatus"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
	    } finally {
			connectionPool.checkIn(connection);
		}
        return users;
    }
	
	@Override
	public boolean delteUser(Integer userId) throws SQLException{
		Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(DELETE_USER_QUERY);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, userId);
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
	public boolean updateUser(UserDTO user) throws SQLException {
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    try {
	        connection = connectionPool.checkOut();
	        pstmt = connection.prepareStatement(UPDATE_USER_QUERY);
	        pstmt.setString(1, user.getName());
	        pstmt.setString(2, user.getSurname());
	        pstmt.setString(3, user.getUsername());
	        pstmt.setString(4, user.getCity());
	        pstmt.setString(5, user.getEmail());
	        pstmt.setBoolean(6, user.getActivated());
	        pstmt.setInt(7, user.getId());
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
}
