package org.unibl.etf.dao;

import java.sql.SQLException;
import java.util.List;

import org.unibl.etf.model.dto.CategoryDTO;

public interface CategoryDAO {
	List<CategoryDTO> getAllCategories() throws Exception;
	boolean deleteCategory(int categoryId) throws SQLException;
	boolean updateCategory(CategoryDTO category) throws SQLException;
}
