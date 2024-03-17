package org.unibl.etf.model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.AttributeDAO;
import org.unibl.etf.dao.impl.AttributeDAOImpl;
import org.unibl.etf.model.dto.AttributeDTO;

public class AttributeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AttributeDAO attributeDAO;
	
	public AttributeBean() {
		this.attributeDAO = new AttributeDAOImpl();
	}
	
	public List<AttributeDTO> getAllAttributesByCategoryId(Integer id){
		List<AttributeDTO> attributes = new ArrayList<>();
		try {
			attributes = this.attributeDAO.getAllAttributesByCategoryId(id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return attributes;
	}
	
}
