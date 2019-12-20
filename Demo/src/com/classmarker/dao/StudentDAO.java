package com.classmarker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.classmarker.dto.QuestionDTO;

public interface StudentDAO {
	public static boolean addstudent(String userenrollment,
			String userid,String contact,String email,String pwd,
			String selectedbranch,String selectedsemester) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt,pstmtmap,pstmtmaprole = null;
		con = CommonDAO.getConnection();
		System.out.println("Before Running update Statement");
		pstmt = con.prepareStatement("insert into user_mst"
				+ "(userenrollment,userid,contact,email,password) "
				+ "values(?,?,?,?,?)");
		pstmt.setString(1, userenrollment);
		pstmt.setString(2,userid);
		pstmt.setString(3, contact);
		pstmt.setString(4, email);
		pstmt.setString(5,pwd);
		int recordcount = pstmt.executeUpdate();
		
		pstmtmaprole = con.prepareStatement("insert into user_role_mapping"
				+ "(userid,roleid) values((select uid from user_mst "
				+ "where userid=?),"
				+ "(select roleid from role_mst where rolename=?))");
		pstmtmaprole.setString(1, userid);
		pstmtmaprole.setString(2, "student");
		pstmtmaprole.executeUpdate();
		
		if(recordcount>0) {
			pstmtmap = con.prepareStatement("insert into "
					+ "userStudent_branch_semester_mapping"
					+ "(userid,branchid,semesterid) "
					+ "values((select uid from user_mst where userid=?),"
					+ "(select branchid from branch_mst where branchname=?),"
					+ "(select semid from semester_mst where semestername=?))");
			pstmtmap.setString(1, userid);
			pstmtmap.setString(2, selectedbranch);
			pstmtmap.setString(3, selectedsemester);
			int recordcountmap = pstmtmap.executeUpdate();
			System.out.println("New Student Added");
			if(recordcountmap>0) {
				return true;
			}
		}
		return false;
	}

	public static boolean addresult(String questionid, String selectedchoice) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("Questionid "+questionid);
		System.out.println("Choice "+selectedchoice);
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		String userid = (String) session.getAttribute("uniqueid");
		Connection con = null;
		PreparedStatement pstmt, pstmt1 = null;
		con = CommonDAO.getConnection();
		
		pstmt1 = con.prepareStatement("update userstudent_question_mapping set status = \"D\" where userstudent_question_mapping.questionid = ?");
		pstmt1.setString(1, questionid);
		int recordcount1 = pstmt1.executeUpdate();
		if(recordcount1 > 0) { 
		pstmt = con.prepareStatement("insert into student_question_choice_mapping(studentid,questionid,choice) values(?,?,?)");
		pstmt.setString(1, "3");
		pstmt.setString(2, questionid);
		pstmt.setString(3, selectedchoice);
		int recordcount = pstmt.executeUpdate();
		if(recordcount > 0) {
		return true;
		}
		
		}
		
		return false;
	}
}
