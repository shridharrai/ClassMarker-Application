package com.classmarker.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.classmarker.dao.CommonDAO;

public class Profile {
	private String userenrollment;
	private String userid;
	private String contact;
	private String email;
	private String password;
	private List<Profile> userlist = new ArrayList<>();
	@Override
	public String toString() {
		return "Profile [userenrollment=" + userenrollment + ", userid=" 
	+ userid + ", contact=" + contact + ", email="
				+ email + ", password=" + password + ", userlist=" + 
	userlist + "]";
	}
	public String getUserenrollment() {
		return userenrollment;
	}
	public void setUserenrollment(String userenrollment) {
		this.userenrollment = userenrollment;
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
	public List<Profile> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<Profile> userlist) {
		this.userlist = userlist;
	}
	
	public String fetchprofile() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Profile profile;
		userlist = new ArrayList<Profile>();
		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select userenrollment,userid,"
				+ "contact,email,password from user_mst where status=\"Y\"");
		rs = pstmt.executeQuery();
		if(rs.next()) {
			profile = new Profile();
			profile.setUserenrollment(rs.getString(1));
			profile.setUserid(rs.getString(2));
			profile.setContact(rs.getString(3));
			profile.setEmail(rs.getString(4));
			profile.setPassword(rs.getString(5));
			System.out.println(profile.toString());
			userlist.add(profile);
			return "success";
		}
		return "fail";
	}
}
