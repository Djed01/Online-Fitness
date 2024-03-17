package org.unibl.etf.model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.CategoryDAO;
import org.unibl.etf.dao.impl.CategoryDAOImpl;
import org.unibl.etf.model.dto.Category;

public class CategoryBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CategoryDAO categoryDAO;
	
	
	public CategoryBean() {
		this.categoryDAO=new CategoryDAOImpl();
	}




	public List<Category> getAllCategories(){
		List<Category> categories = new ArrayList<Category>();
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
	

}
