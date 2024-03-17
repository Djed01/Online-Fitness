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
	
}

