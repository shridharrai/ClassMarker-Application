package com.classmarker.dto;

public class ResultDTO {
	private String marksperquestion;
	private String subjectname;
	private String testname;
	private String subjectcode;
	private String result;
	private String branchname;
	private String semestername;
	private String groupname;
	private String studentname;
	@Override
	public String toString() {
		return "ResultDTO [marksperquestion=" + marksperquestion + ", subjectname=" + subjectname + ", testname="
				+ testname + ", subjectcode=" + subjectcode + ", result=" + result + ", branchname=" + branchname
				+ ", semestername=" + semestername + ", groupname=" + groupname + ", studentname=" + studentname + "]";
	}
	public String getMarksperquestion() {
		return marksperquestion;
	}
	public void setMarksperquestion(String marksperquestion) {
		this.marksperquestion = marksperquestion;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getSubjectcode() {
		return subjectcode;
	}
	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getSemestername() {
		return semestername;
	}
	public void setSemestername(String semestername) {
		this.semestername = semestername;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	
	public ResultDTO() {}
	public ResultDTO(String testname,String marksperquestion, String subjectcode,  String subjectname, String result) {
		super();
		this.marksperquestion = marksperquestion;
		this.subjectname = subjectname;
		this.testname = testname;
		this.subjectcode = subjectcode;
		this.result = result;
	}
	public ResultDTO(String testname,String marksperquestion, String subjectcode, String subjectname,
			String branchname, String semestername, String groupname,String studentname, String result) {
		super();
		this.marksperquestion = marksperquestion;
		this.subjectname = subjectname;
		this.testname = testname;
		this.subjectcode = subjectcode;
		this.result = result;
		this.branchname = branchname;
		this.semestername = semestername;
		this.groupname = groupname;
		this.studentname = studentname;
	}
}
