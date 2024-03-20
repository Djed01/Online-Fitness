package org.unibl.etf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.AttributeDAO;
import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.model.dto.AttributeDTO;

public class AttributeDAOImpl implements AttributeDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SELECT_ALL_ATTRIBUTES_BY_CATEGORY_ID_QUERY = "SELECT * FROM categoryattribute WHERE CategoryID=? AND status=true";
	private static final String DELETE_ATTRIBUTE_QUERY = "UPDATE categoryattribute SET status=? WHERE id = ?";
	private static final String UPDATE_ATTRIBUTE_QUERY = "UPDATE categoryattribute SET name = ? WHERE id = ?";
	private static final String INSERT_ATTRIBUTE_QUERY = "INSERT INTO categoryattribute (name,status,categoryId) VALUES (?,?,?)";
	
	public AttributeDAOImpl() {
		
	}
	
	@Override
	public List<AttributeDTO> getAllAttributesByCategoryId(Integer id) throws SQLException{
		List<AttributeDTO> attributes = new ArrayList<>();
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_ATTRIBUTES_BY_CATEGORY_ID_QUERY);
            pstmt.setInt(1, id);
        	resultSet = pstmt.executeQuery();
        	
        	while(resultSet.next()) {
        		AttributeDTO attribute = new AttributeDTO();
        		attribute.setId(resultSet.getInt("id"));
        		attribute.setName(resultSet.getString("name"));
        		attribute.setCategoryId(resultSet.getInt("categoryId"));
        		attributes.add(attribute);
        	}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return attributes;
	}
	
	
	@Override
    public boolean deleteAttribute(int attributeId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(DELETE_ATTRIBUTE_QUERY);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, attributeId);
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
    public boolean updateAttribute(AttributeDTO attribute) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(UPDATE_ATTRIBUTE_QUERY);
            pstmt.setString(1, attribute.getName());
            pstmt.setInt(2, attribute.getId());
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return false;
    }
	
	@Override
	public boolean insertAttribute(AttributeDTO attribute) throws SQLException{
		 Connection connection = null;
	        PreparedStatement pstmt = null;
	        try {
	            connection = connectionPool.checkOut();
	            pstmt = connection.prepareStatement(INSERT_ATTRIBUTE_QUERY);
	            pstmt.setString(1, attribute.getName());
	            pstmt.setBoolean(2,true);
	            pstmt.setInt(3, attribute.getCategoryId());
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

