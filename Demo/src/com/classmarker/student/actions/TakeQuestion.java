package com.classmarker.student.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.classmarker.dao.CommonDAO;
import com.classmarker.dao.StudentDAO;
import com.classmarker.dto.QuestionDTO;

public class TakeQuestion {
	private List<QuestionDTO> questions;
	private String selectedchoice;
//	private List<String> choices = new ArrayList<String>();
	private String question;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	

	@Override
	public String toString() {
		return "TakeQuestion [questions=" + questions + ", selectedchoice=" + selectedchoice + ", question=" + question
				+ ", choice1=" + choice1 + ", choice2=" + choice2 + ", choice3=" + choice3 + ", choice4=" + choice4
				+ "]";
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





	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	public String getSelectedchoice() {
		return selectedchoice;
	}



	public void setSelectedchoice(String selectedchoice) {
		this.selectedchoice = selectedchoice;
	}



	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
	
	public String fetch() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		System.out.println("inside take ques");
		HttpSession session=ServletActionContext.getRequest().getSession(true);
//		String testid = (String) session.getAttribute("testid");
//		System.out.println("Test id in question mapping " + testid);
		
		pstmt = con.prepareStatement("select questionname,choice1,choice2,"
				+ "choice3,choice4 from test_mst,question_mst,"
				+ "test_question_mapping where "
				+ "test_question_mapping.questionid="
				+ "question_mst.questionid and "
				+ "test_question_mapping.testid=test_mst.testid");
//		pstmt.setString(1, testid);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			questions =  new ArrayList<QuestionDTO>();
		do {
			QuestionDTO question = new QuestionDTO(rs.getString("questionname"), 
					rs.getString("choice1"), rs.getString("choice2"), 
					rs.getString("choice3"), rs.getString("choice4")); 
			questions.add(question);
		}while(rs.next());
		System.out.println(questions);
		session.setAttribute("questions", questions);
			if(showQuestion()) {
				return "success";
			}
		}
		return "fail";
	}
	
	public boolean showQuestion() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt,pstmt1 = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		String userid = (String) session.getAttribute("uniqueid");
		List<QuestionDTO> questions = (List<QuestionDTO>) session.getAttribute("questions");
		System.out.println("Questions in session "+ questions);
		Iterator<QuestionDTO> questionsIterator = questions.iterator();
		while (questionsIterator.hasNext()) {
//			System.out.println(questionsIterator.next().getQuestion());
			QuestionDTO question = questionsIterator.next();
			System.out.println(question);
			System.out.println(question.getQuestion());
			
            pstmt = con.prepareStatement("select status from userstudent_question_mapping where status =\"Y\" and userstudent_question_mapping.studentid = ? and userstudent_question_mapping.questionid = (select questionid from question_mst where questionname=?)");
            pstmt.setString(1, userid);
            pstmt.setString(2, question.getQuestion());
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	System.out.println("Inside first If");
            	setQuestion(question.getQuestion());
            	setChoice1(question.getChoice1());
            	setChoice2(question.getChoice2());
            	setChoice3(question.getChoice3());
            	setChoice4(question.getChoice4());
            	pstmt1 = con.prepareStatement("update userstudent_question_mapping set status = \"N\" where userstudent_question_mapping.questionid = (select questionid from question_mst where questionname=?)");
            	pstmt1.setString(1, question.getQuestion());
            	int recordcount = pstmt1.executeUpdate();
            	if(recordcount > 0) {
            		System.out.println("Inside second If");
            		return true;
            	} 
            }
//            return false;
		}
		return false;
	}
	
	 public String addresult() throws ClassNotFoundException, SQLException {
//		 HttpSession session=ServletActionContext.getRequest().getSession(false);
//		 List<QuestionDTO> questions = (List<QuestionDTO>) session.getAttribute("questions");
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 con = CommonDAO.getConnection();
		 String questionid = null;
		 
		 pstmt = con.prepareStatement("select questionid from userstudent_question_mapping where status=\"N\" ");
		 rs = pstmt.executeQuery();
		 if(rs.next()) {
			questionid = rs.getString("questionid"); 
		 }
		 System.out.println("Questionid "+questionid);
		 System.out.println("Choice "+selectedchoice);
		 if(StudentDAO.addresult(questionid, selectedchoice)) {
			 if(showQuestion()) {
			 return "success";
			 }
		 }
		 return "fail";
	 }
}
