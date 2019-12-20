package com.classmarker.admin.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.classmarker.dao.CommonDAO;

public class LandingAdmin {
	private String usercount;
	private String teachercount;
	private String studentcount;
	private String testcount;
	private String groupcount;
	private String branchcount;
	private String rolecount;
	private String subjectcount;
	private String semestercount;
	private String questioncount;
	@Override
	public String toString() {
		return "LandingAdmin [usercount=" + usercount + ", teachercount=" 
	+ teachercount + ", studentcount="
				+ studentcount + ", testcount=" + testcount + 
				", groupcount=" + groupcount + ", branchcount="
				+ branchcount + ", rolecount=" + rolecount + 
				", subjectcount=" + subjectcount + ", semestercount="
				+ semestercount + ", questioncount=" + questioncount + "]";
	}
	public String getUsercount() {
		return usercount;
	}
	public void setUsercount(String usercount) {
		this.usercount = usercount;
	}
	public String getTeachercount() {
		return teachercount;
	}
	public void setTeachercount(String teachercount) {
		this.teachercount = teachercount;
	}
	public String getStudentcount() {
		return studentcount;
	}
	public void setStudentcount(String studentcount) {
		this.studentcount = studentcount;
	}
	public String getTestcount() {
		return testcount;
	}
	public void setTestcount(String testcount) {
		this.testcount = testcount;
	}
	public String getGroupcount() {
		return groupcount;
	}
	public void setGroupcount(String groupcount) {
		this.groupcount = groupcount;
	}
	public String getBranchcount() {
		return branchcount;
	}
	public void setBranchcount(String branchcount) {
		this.branchcount = branchcount;
	}
	public String getRolecount() {
		return rolecount;
	}
	public void setRolecount(String rolecount) {
		this.rolecount = rolecount;
	}
	public String getSubjectcount() {
		return subjectcount;
	}
	public void setSubjectcount(String subjectcount) {
		this.subjectcount = subjectcount;
	}
	public String getSemestercount() {
		return semestercount;
	}
	public void setSemestercount(String semestercount) {
		this.semestercount = semestercount;
	}
	public String getQuestioncount() {
		return questioncount;
	}
	public void setQuestioncount(String questioncount) {
		this.questioncount = questioncount;
	}
	
	public LandingAdmin() {
		
	}
	public LandingAdmin(String usercount, String teachercount, String studentcount, String testcount, String groupcount,
			String branchcount, String rolecount, String subjectcount, String semestercount, String questioncount) {
		super();
		this.usercount = usercount;
		this.teachercount = teachercount;
		this.studentcount = studentcount;
		this.testcount = testcount;
		this.groupcount = groupcount;
		this.branchcount = branchcount;
		this.rolecount = rolecount;
		this.subjectcount = subjectcount;
		this.semestercount = semestercount;
		this.questioncount = questioncount;
	}
	
	public String fetch() throws ClassNotFoundException, SQLException, NamingException {
		Connection con = null;
		PreparedStatement pstmt,pstmt1,pstmt2,pstmt3,pstmt4,pstmt5,pstmt6,pstmt7,pstmt8,pstmt9 = null;
		ResultSet rs,rs1,rs2,rs3,rs4,rs5,rs6,rs7,rs8,rs9 = null;
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select count(*) from user_mst");
		rs = pstmt.executeQuery();
		pstmt1 = con.prepareStatement("select count(*) from test_mst");
		rs1 = pstmt1.executeQuery();
		pstmt2 = con.prepareStatement("select count(*) from group_mst");
		rs2 = pstmt2.executeQuery();
		pstmt3 = con.prepareStatement("select count(*) from branch_mst");
		rs3 = pstmt3.executeQuery();
		pstmt4 = con.prepareStatement("select count(*) from role_mst");
		rs4 = pstmt4.executeQuery();
		pstmt5 = con.prepareStatement("select count(*) from subject_mst");
		rs5 = pstmt5.executeQuery();
		pstmt6 = con.prepareStatement("select count(*) from semester_mst");
		rs6 = pstmt6.executeQuery();
		pstmt7 = con.prepareStatement("select count(*) from question_mst");
		rs7 = pstmt7.executeQuery();
		pstmt8 = con.prepareStatement("select count(*) from user_mst,"
				+ "user_role_mapping, role_mst where "
				+ "user_role_mapping.userid=user_mst.uid and "
				+ "user_role_mapping.roleid=role_mst.roleid and "
				+ "role_mst.rolename=\"teacher\"");
		rs8 = pstmt8.executeQuery();
		pstmt9 = con.prepareStatement("select count(*) from user_mst,"
				+ "user_role_mapping, role_mst where "
				+ "user_role_mapping.userid=user_mst.uid and "
				+ "user_role_mapping.roleid=role_mst.roleid and "
				+ "role_mst.rolename=\"student\"");
		rs9 = pstmt9.executeQuery();
		if(rs.next()) {
			setUsercount(rs.getString(1));
		}
		if(rs1.next()) {
			setTestcount(rs1.getString(1));
		}
		if(rs2.next()) {
			setGroupcount(rs2.getString(1));
		}
		if(rs3.next()) {
			setBranchcount(rs3.getString(1));
		}
		if(rs4.next()) {
			setRolecount(rs4.getString(1));
		}
		if(rs5.next()) {
			setSubjectcount(rs5.getString(1));
		}
		if(rs6.next()) {
			setSemestercount(rs6.getString(1));
		}
		if(rs7.next()) {
			setQuestioncount(rs7.getString(1));
		}
		if(rs8.next()) {
			setTeachercount(rs8.getString(1));
		}
		if(rs9.next()) {
			setStudentcount(rs9.getString(1));
			return "success";
		 }
		return "fail";
	}
}
