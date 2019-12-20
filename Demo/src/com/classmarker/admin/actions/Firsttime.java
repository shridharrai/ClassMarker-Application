package com.classmarker.admin.actions;

import java.sql.SQLException;

import com.classmarker.dao.ClassmarkerDAO;

public class Firsttime {
	private String message;
	private String userid;
	private String password;
	@Override
	public String toString() {
		return "Firsttime [message=" + message + ", userid=" + 
	userid + ", password=" + password + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String firstlogin() throws ClassNotFoundException, SQLException {
		if(ClassmarkerDAO.startupstatus()==true) {
			if(ClassmarkerDAO.firstlogin(userid, password)==true) {
				System.out.println(userid+password);
				message = "Welcome "+userid;
				return "success";
			}
		}
		else if(ClassmarkerDAO.startupstatus()==true){
			if(ClassmarkerDAO.firstlogin(userid, password)==false) {
				message="Invalid";
				return "partialfail";
			}
		}
		else if(ClassmarkerDAO.startupstatus()==false) {
			return "startupfail";
		}
		return "fail";
	}
	
	public String execute() throws ClassNotFoundException, SQLException {
		if(ClassmarkerDAO.startupstatus()==false) {
			this.message = "Class Marker";
			return "fail";
		}
		this.message = "Class Marker";
		return "success";
	}
}
