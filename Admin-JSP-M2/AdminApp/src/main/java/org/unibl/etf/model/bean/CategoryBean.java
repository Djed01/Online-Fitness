package org.unibl.etf.model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.CategoryDAO;
import org.unibl.etf.dao.impl.CategoryDAOImpl;
import org.unibl.etf.model.dto.CategoryDTO;

public class CategoryBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CategoryDAO categoryDAO;
	
	
	public CategoryBean() {
		this.categoryDAO=new CategoryDAOImpl();
	}
	
	public boolean insertCategory(CategoryDTO categoryDTO) {
		try {
			return this.categoryDAO.insertCategory(categoryDTO);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}




	public List<CategoryDTO> getAllCategories(){
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
		try {
			categories = this.categoryDAO.getAllCategories();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	public boolean deleteCategory(Integer id) {
		try {
			return this.categoryDAO.deleteCategory(id);
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean updateCategory(CategoryDTO category) {
		try {
			return this.categoryDAO.updateCategory(category);
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
