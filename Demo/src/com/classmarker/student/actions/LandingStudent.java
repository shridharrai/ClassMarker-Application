package com.classmarker.student.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.classmarker.dao.CommonDAO;
import com.classmarker.dto.LandingDTO;

public class LandingStudent {
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
		return "LandingStudent [session=" + session + ", uniqueid=" 
	+ uniqueid + ", lands=" + lands + "]";
	}
	
	public String fetch() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		System.out.println("inside landing student fetch");
		pstmt = con.prepareStatement("select distinct(test_mst.testname),"
				+ "group_mst.groupname,subject_mst.subjectname from test_mst,"
				+ "branch_mst,group_mst,user_role_mapping,role_mst,"
				+ "subject_branch_semester_mapping,group_student_mapping,"
				+ "subject_mst,semester_mst,userteacher_branch_semester_subject_mapping,"
				+ "test_branch_semester_subject_group_mapping,user_mst where "
				+ "test_branch_semester_subject_group_mapping.groupid="
				+ "group_student_mapping.groupid and "
				+ "test_branch_semester_subject_group_mapping.testid="
				+ "test_mst.testid and  subject_branch_semester_mapping.subjectid="
				+ "test_branch_semester_subject_group_mapping.subjectid and "
				+ "user_role_mapping.roleid=role_mst.roleid and role_mst.rolename="
				+ "'student' and group_mst.groupid=group_student_mapping.groupid and "
				+ "group_student_mapping.studentid=user_mst.uid and user_mst.uid=?");
//		pstmt.setString(1,uniqueid);
		pstmt.setString(1,"3");
		rs = pstmt.executeQuery();
		if(rs.next()) {
			lands = new ArrayList<LandingDTO>();
		do{
				
			LandingDTO land = new LandingDTO(rs.getString("testname"),
					rs.getString("groupname"),rs.getString("subjectname"));
			lands.add(land);
		}while(rs.next());
			return "success";
		 }
		return "fail";
	}
}
