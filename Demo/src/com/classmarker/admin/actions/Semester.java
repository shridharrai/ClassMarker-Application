package com.classmarker.admin.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.classmarker.dao.AdminDAO;
import com.classmarker.dao.CommonDAO;

public class Semester {
	private String semestername;
	private String semesterdesc;
	private String message;
	ArrayList<Semester> semesterlist = new ArrayList<>();
	@Override
	public String toString() {
		return "Semester [semestername=" + semestername + ", semesterdesc=" 
	+ semesterdesc + ", semesterlist="
				+ semesterlist + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<Semester> getSemesterlist() {
		return semesterlist;
	}
	public void setSemesterlist(ArrayList<Semester> semesterlist) {
		this.semesterlist = semesterlist;
	}
	public String getSemestername() {
		return semestername;
	}
	public void setSemestername(String semestername) {
		this.semestername = semestername;
	}
	public String getSemesterdesc() {
		return semesterdesc;
	}
	public void setSemesterdesc(String semesterdesc) {
		this.semesterdesc = semesterdesc;
	}
	
	public String addsem() throws ClassNotFoundException, SQLException {
		if(AdminDAO.addsem(semestername, semesterdesc)==true) {
			execute();
			this.message = "Added";
			return "success";
		}
		execute();
		return "fail";
	}
	
	public String execute() throws ClassNotFoundException, SQLException {
		System.out.println("Inside FetchSemester....");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		semesterlist = new ArrayList<Semester>();
		Semester semester;
		
		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select semestername,semesterdesc "
				+ "from semester_mst where status=\"Y\"");
		rs = pstmt.executeQuery();
		while(rs.next()) {
			semester = new Semester();
			semester.setSemestername(rs.getString(1));
			semester.setSemesterdesc(rs.getString(2));	
			semesterlist.add(semester);			
		}
		System.out.println(semesterlist);
		return "success";
	}
}
