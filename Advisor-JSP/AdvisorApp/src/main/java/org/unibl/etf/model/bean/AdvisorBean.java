package org.unibl.etf.model.bean;

import org.unibl.etf.dao.AdvisorDAO;
import org.unibl.etf.dao.impl.AdvisorDAOImpl;
import org.unibl.etf.model.dto.AdvisorDTO;

public class AdvisorBean {
	
	private AdvisorDAO advisorDAO;
	
	public AdvisorBean() {
		this.advisorDAO = new AdvisorDAOImpl();
	}
	
	
	public AdvisorDTO login(String username, String password) {
		try {
			return this.advisorDAO.login(username, password);
		}catch (Exception e) {
			e.printStackTrace();
			AdvisorDTO advisorDTO = new AdvisorDTO();
			advisorDTO.setIsLoggedIn(false);
			return advisorDTO;
		}
	}
}
