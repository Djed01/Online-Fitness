package org.unibl.etf.dao;

import java.sql.SQLException;

import org.unibl.etf.model.dto.AdminDTO;

public interface LoginDAO {
	AdminDTO login(String username,String password) throws SQLException;
}
