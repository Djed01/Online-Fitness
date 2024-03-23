package org.unibl.etf.dao;

import java.sql.SQLException;

import org.unibl.etf.model.dto.AdvisorDTO;

public interface AdvisorDAO {
	AdvisorDTO login(String username,String password) throws SQLException;
}