package com.classmarker.teacher.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.classmarker.dao.CommonDAO;
import com.classmarker.dao.TeacherDAO;

public class Test {
	private String testname;
	private String marksperquestion;
	private String selectedbranch;
	private String selectedsemester;
	private String selectedsubject;
	private String selectedgroup;

	List<String> branchlist = new ArrayList<String>();
	List<String> semesterlist = new ArrayList<String>();
	List<String> subjectlist = new ArrayList<String>();
	List<String> grouplist = new ArrayList<String>();


	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getMarksperquestion() {
		return marksperquestion;
	}
	public void setMarksperquestion(String marksperquestion) {
		this.marksperquestion = marksperquestion;
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
	public String getSelectedsubject() {
		return selectedsubject;
	}
	public void setSelectedsubject(String selectedsubject) {
		this.selectedsubject = selectedsubject;
	}
	public String getSelectedgroup() {
		return selectedgroup;
	}
	public void setSelectedgroup(String selectedgroup) {
		this.selectedgroup = selectedgroup;
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
	public List<String> getSubjectlist() {
		return subjectlist;
	}
	public void setSubjectlist(List<String> subjectlist) {
		this.subjectlist = subjectlist;
	}
	public List<String> getGrouplist() {
		return grouplist;
	}
	public void setGrouplist(List<String> grouplist) {
		this.grouplist = grouplist;
	}
	
	public String fetchbranchsemsubjectgroup() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt,pstmt1,pstmt2,pstmt3 = null;
		ResultSet rs,rs1,rs2,rs3 = null;
		
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		String uniqueid = (String) session.getAttribute("uniqueid");
		branchlist = new ArrayList<String>();
		semesterlist = new ArrayList<String>();
		subjectlist = new ArrayList<String>();
		grouplist = new ArrayList<String>();
		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select branchname from "
				+ "branch_mst where status=\"Y\"");
		rs = pstmt.executeQuery();
		pstmt1 = con.prepareStatement("select semestername from "
				+ "semester_mst where status=\"Y\"");
		rs1 = pstmt1.executeQuery();
		pstmt2 = con.prepareStatement("select subjectname from "
				+ "subject_mst where status=\"Y\"");
		rs2 = pstmt2.executeQuery();
		pstmt3 = con.prepareStatement("select groupname from "
				+ "group_mst,group_teacher_mapping where "
				+ "group_teacher_mapping.groupid = group_mst.groupid "
				+ "and group_teacher_mapping.teacherid = ? ");
		pstmt3.setString(1, uniqueid);
		rs3 = pstmt3.executeQuery();
		while(rs.next()) {
			branchlist.add(rs.getString(1));
		}
		while(rs1.next()) {
			semesterlist.add(rs1.getString(1));
		}
		while(rs2.next()) {
			subjectlist.add(rs2.getString(1));
		}
		while(rs3.next()) {
			grouplist.add(rs3.getString(1));
		}
		return "success";
	}
	
	public String addtest() throws ClassNotFoundException, SQLException {
		if(TeacherDAO.addtest(testname,marksperquestion,
				selectedbranch, selectedsemester,selectedsubject,
				selectedgroup)) {
			
			return "success";
		}
		
		return "fail";
	}
}
