package org.unibl.etf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.ConnectionPool;
import org.unibl.etf.dao.DAOUtil;
import org.unibl.etf.dao.QuestionDAO;
import org.unibl.etf.model.dto.QuestionDTO;

public class QuestionDAOImpl implements QuestionDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SEEN_QUERY = "UPDATE question SET seen=? WHERE id = ?";
	private static final String SELECT_QUESTIONS = "SELECT\r\n"
			+ "question.ID, Date, Content, Seen, question.UserID, Name, Surname, Email, ProgramID, Title\r\n"
			+ "FROM fitness.question\r\n"
			+ "LEFT JOIN user ON fitness.question.UserID = user.ID\r\n"
			+ "LEFT JOIN program ON fitness.question.ProgramID = program.ID";
	
	@Override
	public List<QuestionDTO> getAllQuestions() throws SQLException{
		List<QuestionDTO> questions = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        Object values[] = {};
        try {
        	connection = connectionPool.checkOut();
        	
        	PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SELECT_QUESTIONS, false, values);
        	resultSet = pstmt.executeQuery();	
        	 
            while (resultSet.next()) {
            	QuestionDTO question = new QuestionDTO();
            	question.setId(resultSet.getInt("id"));
            	question.setDate(resultSet.getDate("date"));
            	question.setContent(resultSet.getString("content"));
            	question.setSeen(resultSet.getBoolean("seen"));
            	question.setUserId(resultSet.getInt("userId"));
            	question.setUserName(resultSet.getString("name"));
            	question.setUserSurname(resultSet.getString("surname"));
            	question.setUserEmail(resultSet.getString("email"));
            	question.setProgramId(resultSet.getInt("programId"));
            	question.setProgramTitle(resultSet.getString("title"));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
	    } finally {
			connectionPool.checkIn(connection);
		}
        return questions;
	}
	
	@Override
	public Boolean setSeen(Integer id) throws SQLException{
		Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connectionPool.checkOut();
            pstmt = connection.prepareStatement(SEEN_QUERY);
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, id);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return false;
	}
}
