package org.unibl.etf.dao;

import java.sql.SQLException;
import java.util.List;

import org.unibl.etf.model.dto.Category;

public interface CategoryDAO {
	List<Category> getAllCategories() throws Exception;
	boolean deleteCategory(int categoryId) throws SQLException;
}
