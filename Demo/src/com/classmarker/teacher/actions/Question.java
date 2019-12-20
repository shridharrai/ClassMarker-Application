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

public class Question {
	private String questionid;
	private String question;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private String selectedchoice;
	private String selectedquestions;
	List<Question> questionbanklist = new ArrayList<Question>();
	List<String> questionidlist = new ArrayList<String>();
	HttpSession session = ServletActionContext.getRequest().getSession(false);
	String uniqueid = (String) session.getAttribute("uniqueid");

	@Override
	public String toString() {
		return "Question [questionid=" + questionid + ", question=" + question + ", choice1=" + choice1 + ", choice2="
				+ choice2 + ", choice3=" + choice3 + ", choice4=" + choice4 + ", selectedchoice=" + selectedchoice
				+ ", selectedquestions=" + selectedquestions + ", questionbanklist=" + questionbanklist
				+ ", questionidlist=" + questionidlist + ", session=" + session + ", uniqueid=" + uniqueid + "]";
	}

	public String getSelectedquestions() {
		return selectedquestions;
	}

	public void setSelectedquestions(String selectedquestions) {
		this.selectedquestions = selectedquestions;
	}

	public List<String> getQuestionidlist() {
		return questionidlist;
	}

	public void setQuestionidlist(List<String> questionidlist) {
		this.questionidlist = questionidlist;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public List<Question> getQuestionbanklist() {
		return questionbanklist;
	}

	public void setQuestionbanklist(List<Question> questionbanklist) {
		this.questionbanklist = questionbanklist;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public String getSelectedchoice() {
		return selectedchoice;
	}

	public void setSelectedchoice(String selectedchoice) {
		this.selectedchoice = selectedchoice;
	}

	public String addquestion() throws ClassNotFoundException, SQLException {
		if (TeacherDAO.addquestion(question, choice1, choice2, choice3, choice4, selectedchoice)) {
			return "success";
		}

		return "fail";
	}
	
	public String addquestionFromBank() throws ClassNotFoundException, SQLException {
		if (TeacherDAO.addquestionFromBank(selectedquestions)) {
			return "success";
		}
		
		return "fail";
	}

	public String questionbank() throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		questionbanklist = new ArrayList<Question>();

		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select question_mst.questionid,"
				+ "question_mst.questionname," 
		+ "question_mst.choice1,question_mst.choice2,"
		+ "question_mst.choice3,question_mst.choice4," 
		+ "question_mst.correctchoice,subject_mst.subjectname "
		+ "from question_mst,subject_mst,question_subject_mapping,"
		+ "userteacher_branch_semester_subject_mapping where "
		+ "question_mst.questionid=question_subject_mapping.questionid "
		+ "and subject_mst.sid=question_subject_mapping.subjectid and "
		+ "subject_mst.sid=userteacher_branch_semester_subject_mapping.subjectid "
		+ "and  userteacher_branch_semester_subject_mapping.userid=?");
		pstmt.setString(1, uniqueid);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			Question ques = new Question();
	       	ques.setQuestionid(rs.getString(1));
	       	questionidlist.add(rs.getString(1));
			ques.setQuestion(rs.getString(2));
			ques.setChoice1(rs.getString(3));
			ques.setChoice2(rs.getString(4));
			ques.setChoice3(rs.getString(5));
			ques.setChoice4(rs.getString(6));
			ques.setSelectedchoice(rs.getString(7));
			
			questionbanklist.add(ques);
		}
		System.out.println(uniqueid);
		System.out.println(questionidlist);
		System.out.println(questionbanklist);
		System.out.println("Inside question bank");
		return "success";
	}
	
	public String execute() {
		return "success";
	}
}
