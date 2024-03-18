package org.unibl.etf.dao.impl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.CategoryDAO;
import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.dao.DAOUtil;
import org.unibl.etf.model.dto.CategoryDTO;

public class CategoryDAOImpl implements CategoryDAO {

	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_CATEGORIES_QUERY = "SELECT * FROM category WHERE status=true";
    private static final String INSERT_CATEGORY_QUERY = "INSERT INTO category (name,status) VALUES (?,?)";
    private static final String DELETE_CATEGORY_QUERY = "UPDATE category SET status=? WHERE id = ?";
    private static final String UPDATE_CATEGORY_QUERY = "UPDATE category SET name = ? WHERE id = ?";

    public CategoryDAOImpl() {
    	
    }
    
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        Object values[] = {};
        try {
        	connection = connectionPool.checkOut();
        	
        	PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ALL_CATEGORIES_QUERY, false, values);
        	resultSet = pstmt.executeQuery();	
        	 
            while (resultSet.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
	    } finally {
			connectionPool.checkIn(connection);
		}
        return categories;
    }
    
    @Override
    public boolean insertCategory(CategoryDTO category) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(INSERT_CATEGORY_QUERY);
            pstmt.setString(1, category.getName());
            pstmt.setBoolean(2,true);;
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return false;
    }
    
    @Override
    public boolean deleteCategory(int categoryId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(DELETE_CATEGORY_QUERY);
            pstmt.setBoolean(1, false);
            pstmt.setInt(2, categoryId);
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
    public boolean updateCategory(CategoryDTO category) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(UPDATE_CATEGORY_QUERY);
            pstmt.setString(1, category.getName());
            pstmt.setInt(2, category.getId());
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return false;
    }
}
