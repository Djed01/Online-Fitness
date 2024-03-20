package org.unibl.etf.dao;

import java.sql.SQLException;
import java.util.List;

import org.unibl.etf.model.dto.LogDTO;

public interface LogDAO {
	List<LogDTO> getAllLogs() throws SQLException;
}
