package com.classmarker.teacher.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.classmarker.dao.CommonDAO;
import com.classmarker.dto.LandingDTO;

public class LandingTeacher {
	HttpSession session=ServletActionContext.getRequest().getSession(false);
	String uniqueid = (String) session.getAttribute("uniqueid");
	private List<LandingDTO> lands;
	public List<LandingDTO> getLands() {
		return lands;
	}
	public void setLands(List<LandingDTO> lands) {
		this.lands = lands;
	}
	@Override
	public String toString() {
		return "LandingTeacher [lands=" + lands + "]";
	}
	
	public String fetch() throws ClassNotFoundException, SQLException, NamingException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select test_mst.testname,"
				+ "test_mst.marksperquestion,branch_mst.branchname,"
				+ "group_mst.groupname,semester_mst.semestername, "
				+ "subject_mst.subjectname, user_mst.userid from "
				+ "test_mst,branch_mst,group_mst,subject_mst,"
				+ "semester_mst,userteacher_branch_semester_subject_mapping,"
				+ "test_branch_semester_subject_group_mapping,user_mst where "
				+ "test_branch_semester_subject_group_mapping.testid=test_mst.testid and "
				+ "user_mst.uid=userteacher_branch_semester_subject_mapping.userid and "
				+ "test_branch_semester_subject_group_mapping.branchid=branch_mst.branchid "
				+ "and test_branch_semester_subject_group_mapping.semesterid"
				+ "=semester_mst.semid and "
				+ "test_branch_semester_subject_group_mapping.subjectid"
				+ "=subject_mst.sid and "
				+ "test_branch_semester_subject_group_mapping.groupid"
				+ "=group_mst.groupid and  "
				+ "test_branch_semester_subject_group_mapping.subjectid"
				+ "=userteacher_branch_semester_subject_mapping.subjectid "
				+ "and userteacher_branch_semester_subject_mapping.userid=?");
		pstmt.setString(1,uniqueid);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			lands = new ArrayList<LandingDTO>();
		do{
				
			LandingDTO land = new LandingDTO(rs.getString("testname"),
					rs.getString("marksperquestion"),
					rs.getString("branchname"),rs.getString("groupname"),
					rs.getString("semestername"),rs.getString("subjectname"),
					rs.getString("userid"));
			lands.add(land);
		}while(rs.next());
			return "success";
		 }
		return "fail";
	}
}
