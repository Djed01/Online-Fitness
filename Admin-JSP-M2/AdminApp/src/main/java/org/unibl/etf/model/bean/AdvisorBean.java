package org.unibl.etf.model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.AdvisorDAO;
import org.unibl.etf.dao.impl.AdvisorDAOImpl;
import org.unibl.etf.model.dto.AdvisorDTO;

public class AdvisorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AdvisorDAO advisorDAO;
	
	public AdvisorBean() {
		advisorDAO = new AdvisorDAOImpl();
	}
	
	public List<AdvisorDTO> getAllAdvisors(){
		List<AdvisorDTO> advisors = new ArrayList<>();
		try {
			advisors = this.advisorDAO.getAllAdvisors();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return advisors;
	}
	
	public boolean deleteAdvisor(Integer advisorId) {
		try {
			return this.advisorDAO.deleteAdvisor(advisorId);
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateAdvisor(AdvisorDTO advisorDTO) {
		try {
			return this.advisorDAO.updateAdvisor(advisorDTO);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addNewAdvisor(AdvisorDTO advisorDTO) {
		try {
			return this.advisorDAO.insertAdvisor(advisorDTO);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
