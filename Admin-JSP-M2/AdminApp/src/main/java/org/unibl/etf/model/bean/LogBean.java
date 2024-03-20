package org.unibl.etf.model.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.LogDAO;
import org.unibl.etf.dao.impl.LogDAOImpl;
import org.unibl.etf.model.dto.LogDTO;

public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LogDAO logDAO;
	
	public LogBean() {
		this.logDAO = new LogDAOImpl();
	}
	
	public List<LogDTO> getAllLogs(){
			List<LogDTO> logs = new ArrayList<>();
			try {
				logs = this.logDAO.getAllLogs();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return logs;
		}

}
