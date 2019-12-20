package com.classmarker.admin.actions;

import java.sql.SQLException;

import com.classmarker.dao.ClassmarkerDAO;

public class Firstupdatepwd {
	private String userid;
	private String contact;
	private String email;
	private String password;
	@Override
	public String toString() {
		return "Firstupdatepwd [userid=" + userid + ", contact=" + contact + 
				", email=" + email + ", password="
				+ password + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String firstupdatepwd() throws ClassNotFoundException, SQLException {
		if(ClassmarkerDAO.
				firstupdatepwd(userid, contact, email, password)==true) {
			if(ClassmarkerDAO.startupfinish()==true) {
				return "success";
			}
		}
		return "fail";
	}
}
