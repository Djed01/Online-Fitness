package org.unibl.etf.model.dto;

import java.util.Date;

public class QuestionDTO {
	
	private Integer id;
	private Date date;
	private String content;
	private Boolean seen;
	private Integer userId;
	private String userName;
	private String userSurname;
	private String userEmail;
	private Integer programId;
	private String programTitle;
	
	public QuestionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionDTO(Integer id, Date date, String content, Boolean seen, Integer userId, String userName,
			String userSurname, String userEmail, Integer programId, String programTitle) {
		super();
		this.id = id;
		this.date = date;
		this.content = content;
		this.seen = seen;
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userEmail = userEmail;
		this.programId = programId;
		this.programTitle = programTitle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getSeen() {
		return seen;
	}

	public void setSeen(Boolean seen) {
		this.seen = seen;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public String getProgramTitle() {
		return programTitle;
	}

	public void setProgramTitle(String programTitle) {
		this.programTitle = programTitle;
	}
	
	
	
}
