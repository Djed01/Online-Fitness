package org.unibl.etf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.dao.DAOUtil;
import org.unibl.etf.dao.LogDAO;
import org.unibl.etf.model.dto.LogDTO;

public class LogDAOImpl implements LogDAO{

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL_LOGS_QUERY = "SELECT * FROM log";
	
	public LogDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<LogDTO> getAllLogs() throws SQLException{
		List<LogDTO> logs = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        Object values[] = {};
        try {
        	connection = connectionPool.checkOut();
        	
        	PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_ALL_LOGS_QUERY, false, values);
        	resultSet = pstmt.executeQuery();	
        	 
            while (resultSet.next()) {
                LogDTO log = new LogDTO();
                log.setLevel(resultSet.getString("type"));
                log.setTime(resultSet.getDate("date"));
                log.setDescription(resultSet.getString("description"));
            	logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
	    } finally {
			connectionPool.checkIn(connection);
		}
        return logs;
	}
	
	
}
