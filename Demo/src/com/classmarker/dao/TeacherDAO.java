package com.classmarker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

public interface TeacherDAO {
	HttpSession session = ServletActionContext.getRequest().getSession(false);
	String uniqueid = (String) session.getAttribute("uniqueid");

	public static boolean addteacher(String userenrollment, String userid, String contact, String email, String pwd,
			String selectedbranch, String selectedsemester, String selectedsubject)
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt, pstmtmap, pstmtmaprole = null;
		String regex = ",";
		String[] branches = selectedbranch.split(regex);
		String[] semesters = selectedsemester.split(regex);
		String[] subjects = selectedsubject.split(regex);
		con = CommonDAO.getConnection();
		System.out.println("Before Running update Statement");
		pstmt = con.prepareStatement(
				"insert into user_mst" + "(userenrollment,userid,contact,email,password) " + "values(?,?,?,?,?)");
		pstmt.setString(1, userenrollment);
		pstmt.setString(2, userid);
		pstmt.setString(3, contact);
		pstmt.setString(4, email);
		pstmt.setString(5, pwd);
		int recordcount = pstmt.executeUpdate();
		pstmtmaprole = con
				.prepareStatement("insert into user_role_mapping" + "(userid,roleid) values((select uid from user_mst "
						+ "where userid=?)," + "(select roleid from role_mst where rolename=?))");
		pstmtmaprole.setString(1, userid);
		pstmtmaprole.setString(2, "teacher");
		pstmtmaprole.executeUpdate();
		if (recordcount > 0) {
			for (String branchstr : branches) {
				for (String semesterstr : semesters) {
					for (String subjectstr : subjects) {

						pstmtmap = con.prepareStatement("insert into " + "userteacher_branch_semester_subject_mapping"
								+ "(userid,branchid,semesterid,subjectid) "
								+ "values((select uid from user_mst where userid=?),"
								+ "(select branchid from branch_mst where branchname=?),"
								+ "(select semid from semester_mst where semestername=?),"
								+ "(select sid from subject_mst where subjectname=?))");
						pstmtmap.setString(1, userid);
						pstmtmap.setString(2, branchstr.trim());
						pstmtmap.setString(3, semesterstr.trim());
						pstmtmap.setString(4, subjectstr.trim());
						int recordcountmap = pstmtmap.executeUpdate();
					}
				}
			}
			System.out.println("New Teacher Added");
			return true;
		}
		return false;
	}

	public static boolean addgroup(String groupname, String groupdesc, String selectedstudent)
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt, pstmtmap, pstmtteachermap = null;
		System.out.println("Uniqueid in teacherDAO " + uniqueid);
		HttpSession session = ServletActionContext.getRequest().getSession(true);
		session.setAttribute("selectedstudents", selectedstudent);
		
		String regex = ",";
		String[] students = selectedstudent.split(regex);

		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("insert into group_mst" + "(groupname,groupdesc) values(?,?)");
		pstmt.setString(1, groupname);
		pstmt.setString(2, groupdesc);
		int recordCount = pstmt.executeUpdate();
		if (recordCount > 0) {
			pstmtteachermap = con.prepareStatement("insert into " + "group_teacher_mapping(groupid,teacherid) "
					+ "values((select groupid from group_mst " + "where groupname=?),?)");
			pstmtteachermap.setString(1, groupname);
			pstmtteachermap.setString(2, uniqueid);
			pstmtteachermap.executeUpdate();
		}
		if (recordCount > 0) {
			for (String str : students) {
				pstmtmap = con.prepareStatement("insert into " + "group_student_mapping(groupid,studentid) "
						+ "values((select groupid from group_mst where "
						+ "groupname=?),(select uid from user_mst where " + "userid=?))");
				pstmtmap.setString(1, groupname);
				pstmtmap.setString(2, str.trim());
				pstmtmap.executeUpdate();
				System.out.println(str.trim());
			}
			System.out.println("Group Added");
			return true;
		}
		return false;
	}

	public static boolean addtest(String testname, String marksperquestion, String selectedbranch,
			String selectedsemester, String selectedsubject, String selectedgroup)
			throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt, pstmt2, pstmt3, pstmt4, pstmtteachermap = null;
		ResultSet rs, rs1 = null;
		System.out.println(selectedgroup);
		HttpSession session = ServletActionContext.getRequest().getSession(true);
		session.setAttribute("selectedgroups", selectedgroup);
		String regex = ",";
		String[] groups = selectedgroup.split(regex);
		System.out.println("Uniqueid in teacherDAO " + uniqueid);

		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("insert into test_mst(testname," 
		+ "marksperquestion) values(?,?)");
		pstmt.setString(1, testname);
		pstmt.setString(2, marksperquestion);
		int recordCount = pstmt.executeUpdate();

		if (recordCount > 0) {
			pstmtteachermap = con.prepareStatement("insert into " + 
		"userteacher_test_mapping(teacherid,testid) "
					+ "values(?,(select testid from test_mst where " + "testname=?))");
			pstmtteachermap.setString(1, uniqueid);
			pstmtteachermap.setString(2, testname);
			pstmtteachermap.executeUpdate();
		}
		if (recordCount > 0) {
			for (int i = 0; i < groups.length; i++) {
				pstmt3 = con.prepareStatement("insert into " + "test_branch_semester_subject_group_mapping"
						+ "(testid,branchid,semesterid,subjectid," + "groupid) values((select testid from test_mst "
						+ "where testname=?),(select branchid from " + "branch_mst where branchname=?),(select semid "
						+ "from semester_mst where semestername=?)," + "(select sid from subject_mst where "
						+ "subjectname=?),(select groupid from group_mst " + "where groupname=?))");
				pstmt3.setString(1, testname);
				pstmt3.setString(2, selectedbranch);
				pstmt3.setString(3, selectedsemester);
				pstmt3.setString(4, selectedsubject);
				pstmt3.setString(5, groups[i].trim());
				pstmt3.executeUpdate();
			}

			System.out.println(testname);
			System.out.println(marksperquestion);
			System.out.println(selectedbranch + selectedsemester + selectedsubject + selectedgroup);
			pstmt2 = con.prepareStatement("select testid from test_mst " + "where testname=?");
			pstmt2.setString(1, testname);
			rs = pstmt2.executeQuery();
			if (rs.next()) {
				String testid = rs.getString(1);
				System.out.println("Test id is " + testid);
				session.setAttribute("testid", testid);
			}
			pstmt4 = con.prepareStatement("select sid from " + "subject_mst where subjectname=?");
			pstmt4.setString(1, selectedsubject);
			rs1 = pstmt4.executeQuery();
			if (rs1.next()) {
				String subjectid = rs1.getString(1);
				System.out.println("Subject id is " + subjectid);
				session.setAttribute("subjectid", subjectid);
			}
			return true;
		}
		return false;
	}

	public static boolean addquestion(String question, String choice1, String choice2, String choice3, String choice4,
			String selectedcorrectchoice) throws SQLException, ClassNotFoundException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt1, pstmt2, pstmt3, pstmt4, pstmt5 = null;
		con = CommonDAO.getConnection();
		String selectedgroup = (String) session.getAttribute("selectedgroups");
		System.out.println(selectedgroup);
		String regex = ",";
		String[] groups = selectedgroup.split(regex);
		String testid = (String) session.getAttribute("testid");
		String subjectid = (String) session.getAttribute("subjectid");
		System.out.println("Test id in question mapping " + testid);
		System.out.println("Subject id in question mapping " + subjectid);
		pstmt1 = con.prepareStatement("insert into question_mst" + "(questionname,choice1,choice2,choice3,choice4,"
				+ "correctchoice) values(?,?,?,?,?,?)");
		pstmt1.setString(1, question);
		pstmt1.setString(2, choice1);
		pstmt1.setString(3, choice2);
		pstmt1.setString(4, choice3);
		pstmt1.setString(5, choice4);
		pstmt1.setString(6, selectedcorrectchoice);
		int recordCount = pstmt1.executeUpdate();
		if (recordCount > 0) {
			pstmt2 = con.prepareStatement("insert into " + "test_question_mapping(testid,questionid) "
					+ "values(?,(select questionid from " + "question_mst where questionname=?))");
			pstmt2.setString(1, testid);
			pstmt2.setString(2, question);
			int recordCount2 = pstmt2.executeUpdate();

			if (recordCount2 > 0) {
				pstmt3 = con.prepareStatement("insert into " + "question_subject_mapping(questionid,"
						+ "subjectid) values((select questionid from " + "question_mst where questionname=?),?)");
				pstmt3.setString(1, question);
				pstmt3.setString(2, subjectid);
				int recordCount3 = pstmt3.executeUpdate();
				int recordcount4 = 0;
				if (recordCount3 > 0) {
					for (String str : groups) {
						pstmt5 = con.prepareStatement("select studentid from group_student_mapping,group_mst where group_student_mapping.groupid = group_mst.groupid and groupname = ?");
						pstmt5.setString(1, str.trim());
						rs = pstmt5.executeQuery();
						if(rs.next()) {
					pstmt4 = con.prepareStatement("insert into userstudent_question_mapping(studentid,questionid) values(?,(select questionid from question_mst where questionname= ?))");
					pstmt4.setString(1, rs.getString("studentid"));
					pstmt4.setString(2, question);
					recordcount4 = pstmt4.executeUpdate();
						}
					}
					return true;
//					if(recordcount4 > 0) {
//						return true;
//					}
				}
			}

		}
		return false;

	}

	public static boolean addquestionFromBank(String selectedquestions) 
			throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		con = CommonDAO.getConnection();
		
		
		String regex=",";
	    String[] questions=selectedquestions.split(regex);
	    String testid = (String) session.getAttribute("testid");
	    
	    int recordCount = 0;
	    for(int i=0;i<questions.length;i++) {
	    	pstmt1 = con.prepareStatement("insert into test_question_mapping"
	    			+ "(testid,questionid) values(?,?)");
			pstmt1.setString(1, testid);
			pstmt1.setString(2, questions[i]);
			recordCount = pstmt1.executeUpdate();
			
	    }
	    
	    if(recordCount>0) {
			return true;
		}
	    return false;
	    
	}
	
	
}
