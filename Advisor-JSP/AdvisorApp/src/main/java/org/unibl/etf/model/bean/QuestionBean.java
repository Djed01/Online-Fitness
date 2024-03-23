package org.unibl.etf.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.dao.QuestionDAO;
import org.unibl.etf.dao.impl.QuestionDAOImpl;
import org.unibl.etf.model.dto.QuestionDTO;

public class QuestionBean {
	
	private QuestionDAO questionDAO;
	
	public QuestionBean() {
		this.questionDAO = new QuestionDAOImpl();
	}
	
	public List<QuestionDTO> getAllQuestions(){
		try {
			return this.questionDAO.getAllQuestions();
		}catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<QuestionDTO>();
		}
	}
	
	public Boolean setSeen(Integer id) {
		try {
			return this.questionDAO.setSeen(id);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
