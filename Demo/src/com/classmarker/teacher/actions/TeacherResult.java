package com.classmarker.teacher.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.classmarker.dao.CommonDAO;
import com.classmarker.dto.ResultDTO;

public class TeacherResult {
	private List<ResultDTO> lands;

	public List<ResultDTO> getLands() {
		return lands;
	}

	public void setLands(List<ResultDTO> lands) {
		this.lands = lands;
	}

	@Override
	public String toString() {
		return "TeacherResult [lands=" + lands + "]";
	}
	
	public String fetch() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		System.out.println("inside student result fetch");
		pstmt = con.prepareStatement("select test_mst.testname,test_mst.marksperquestion,subject_mst.subjectcode,subject_mst.subjectname,branch_mst.branchname,semester_mst.semestername,group_mst.groupname,user_mst.userid,student_test_result_mapping.result from test_mst,student_test_result_mapping,test_branch_semester_subject_group_mapping,user_mst,subject_mst,group_mst,semester_mst,branch_mst where test_mst.testid=student_test_result_mapping.testid and test_branch_semester_subject_group_mapping.testid=student_test_result_mapping.testid and test_branch_semester_subject_group_mapping.subjectid=subject_mst.sid and branch_mst.branchid=test_branch_semester_subject_group_mapping.branchid and semester_mst.semid=test_branch_semester_subject_group_mapping.semesterid and group_mst.groupid=test_branch_semester_subject_group_mapping.groupid and user_mst.uid=student_test_result_mapping.studentid;");
//		pstmt.setString(1,uniqueid);
//		pstmt.setString(1,"1");
		rs = pstmt.executeQuery();
		if(rs.next()) {
			lands = new ArrayList<ResultDTO>();
		do{
				
			ResultDTO land = new ResultDTO(rs.getString("testname"),rs.getString("marksperquestion"),rs.getString("subjectcode"),rs.getString("subjectname"),rs.getString("branchname"),rs.getString("semestername"),rs.getString("groupname"),rs.getString("userid"),rs.getString("result"));
			lands.add(land);
		}while(rs.next());
			return "success";
		 }
		return "fail";
	}
}
