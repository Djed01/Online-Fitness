package org.unibl.etf.dao;

import java.sql.SQLException;
import java.util.List;

import org.unibl.etf.model.dto.AdvisorDTO;

public interface AdvisorDAO {
	List<AdvisorDTO> getAllAdvisors() throws SQLException;
	boolean deleteAdvisor(Integer id) throws SQLException;
	boolean updateAdvisor(AdvisorDTO advisorDTO) throws SQLException;
	boolean insertAdvisor(AdvisorDTO advisorDTO) throws SQLException;
}
