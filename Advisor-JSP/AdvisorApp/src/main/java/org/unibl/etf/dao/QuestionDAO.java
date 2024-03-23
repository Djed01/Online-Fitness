package org.unibl.etf.dao;

import java.sql.SQLException;
import java.util.List;

import org.unibl.etf.model.dto.QuestionDTO;

public interface QuestionDAO {
	List<QuestionDTO> getAllQuestions() throws SQLException;
	Boolean setSeen(Integer id) throws SQLException;
}
