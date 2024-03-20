package org.unibl.etf.model.dto;

import java.io.Serializable;
import java.util.Date;

public class LogDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String level;
    private Date time;
    private String description;
    
	public LogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogDTO(String level, Date time, String description) {
		super();
		this.level = level;
		this.time = time;
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
    
    
}
