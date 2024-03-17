package org.unibl.etf.dao;

import java.sql.SQLException;
import java.util.List;

import org.unibl.etf.model.dto.AttributeDTO;

public interface AttributeDAO {
	List<AttributeDTO> getAllAttributesByCategoryId(Integer id) throws SQLException;
}
