package com.classmarker.admin.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.classmarker.dao.AdminDAO;
import com.classmarker.dao.ClassmarkerDAO;
import com.classmarker.dao.CommonDAO;

public class Subject {
	private int subjectid;
	private String subjectcode;
	private String subjectname;
	private String subjectdesc;
	private String selectedbranch;
	private String selectedsemester;
	private String message;
	List<String> branchlist;
	List<String> semesterlist;
	List<Subject> subjectlist;
	@Override
	public String toString() {
		return "Subject [subjectid=" + subjectid + ", subjectcode=" + 
	subjectcode + ", subjectname=" + subjectname
				+ ", subjectdesc=" + subjectdesc + ", selectedbranch=" + 
	selectedbranch + ", selectedsemester="
				+ selectedsemester + ", branchlist=" + branchlist + 
				", semesterlist=" + semesterlist + ", subjectlist="
				+ subjectlist + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Subject> getSubjectlist() {
		return subjectlist;
	}
	public void setSubjectlist(List<Subject> subjectlist) {
		this.subjectlist = subjectlist;
	}
	public int getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}
	public String getSubjectcode() {
		return subjectcode;
	}
	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	public String getSubjectdesc() {
		return subjectdesc;
	}
	public void setSubjectdesc(String subjectdesc) {
		this.subjectdesc = subjectdesc;
	}
	public String getSelectedbranch() {
		return selectedbranch;
	}
	public void setSelectedbranch(String selectedbranch) {
		this.selectedbranch = selectedbranch;
	}
	public String getSelectedsemester() {
		return selectedsemester;
	}
	public void setSelectedsemester(String selectedsemester) {
		this.selectedsemester = selectedsemester;
	}
	public List<String> getBranchlist() {
		return branchlist;
	}
	public void setBranchlist(List<String> branchlist) {
		this.branchlist = branchlist;
	}
	public List<String> getSemesterlist() {
		return semesterlist;
	}
	public void setSemesterlist(List<String> semesterlist) {
		this.semesterlist = semesterlist;
	}
	
	public String fetchbranchsem() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt,pstmt1 = null;
		ResultSet rs,rs1 = null;

		branchlist = new ArrayList<String>();
		semesterlist = new ArrayList<String>();
		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select branchname from "
				+ "branch_mst where status=\"Y\"");
		rs = pstmt.executeQuery();
		pstmt1 = con.prepareStatement("select semestername from "
				+ "semester_mst where status=\"Y\"");
		rs1 = pstmt1.executeQuery();
		while(rs.next()) {
			branchlist.add(rs.getString(1));
		}
		while(rs1.next()) {
			semesterlist.add(rs1.getString(1));
		}
		return "success";
	}
	
	public String addsubject() throws ClassNotFoundException, SQLException {
		if(AdminDAO.addsub(subjectcode, subjectid, 
				subjectname, subjectdesc,selectedbranch,
				selectedsemester)) {
			Connection con = null;
			PreparedStatement pstmt,pstmt1 = null;
			ResultSet rs,rs1 = null;

			branchlist = new ArrayList<String>();
			semesterlist = new ArrayList<String>();
			
			con = CommonDAO.getConnection();
			pstmt = con.prepareStatement("select branchname from "
					+ "branch_mst where status=\"Y\"");
			rs = pstmt.executeQuery();
			pstmt1 = con.prepareStatement("select semestername from "
					+ "semester_mst where status=\"Y\"");
			rs1 = pstmt1.executeQuery();
			while(rs.next()) {
				branchlist.add(rs.getString(1));
			}
			while(rs1.next()) {
				semesterlist.add(rs1.getString(1));
			}
			execute();
			this.message = "Added";
			return "success";
		}
		execute();
		return "fail";
	}
	
	public String execute() throws ClassNotFoundException, SQLException {
		System.out.println("Inside execute...");
		Connection con = null;
		PreparedStatement pstmt,pstmt1,pstmt2 = null;
		ResultSet rs,rs1,rs2 = null;
		
		subjectlist = new ArrayList<Subject>();
		Subject subject = new Subject();
		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select subject_mst.subjectcode, "
				+ "subject_mst.subjectid, subject_mst.subjectname, "
				+ "subject_mst.subjectdesc,"
				+ " branch_mst.branchname, semester_mst.semestername "
				+ "from subject_mst, branch_mst, semester_mst, "
				+ "subject_branch_semester_mapping where "
				+ "subject_mst.sid=subject_branch_semester_mapping.subjectid "
				+ "and branch_mst.branchid=subject_branch_semester_mapping.branchid "
				+ "and "
				+ "semester_mst.semid=subject_branch_semester_mapping.semesterid;");
		rs = pstmt.executeQuery();
		while(rs.next()) {
			subject = new Subject();
			subject.setSubjectcode(rs.getString(1));
			subject.setSubjectid(rs.getInt(2));	
			subject.setSubjectname(rs.getString(3));
			subject.setSubjectdesc(rs.getString(4));
			subject.setSelectedbranch(rs.getString(5));
			subject.setSelectedsemester(rs.getString(6));
			subjectlist.add(subject);
			
		}

		branchlist = new ArrayList<String>();
		semesterlist = new ArrayList<String>();
		
		pstmt1 = con.prepareStatement("select branchname from "
				+ "branch_mst where status=\"Y\"");
		rs1 = pstmt1.executeQuery();
		pstmt2 = con.prepareStatement("select semestername from "
				+ "semester_mst where status=\"Y\"");
		rs2 = pstmt2.executeQuery();
		while(rs1.next()) {
			branchlist.add(rs1.getString(1));
		}
		while(rs2.next()) {
			semesterlist.add(rs2.getString(1));
		}
		System.out.println(subjectlist);
		return "success";
	}
}
