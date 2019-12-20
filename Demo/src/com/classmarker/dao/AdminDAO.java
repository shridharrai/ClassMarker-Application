package com.classmarker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminDAO {
	public static boolean addsub(String subjectcode,int subjectid,
			String subjectname,String subjectdesc,String selectedbranch,
			String selectedsemester) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt,pstmtmap = null;
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("insert into subject_mst"
				+ "(subjectcode,subjectid,subjectname,subjectdesc) "
				+ "values(?,?,?,?)");
		pstmt.setString(1, subjectcode);
		pstmt.setInt(2, subjectid);
		pstmt.setString(3, subjectname);
		pstmt.setString(4, subjectdesc);
		int recordCount = pstmt.executeUpdate();
		pstmtmap = con.prepareStatement
				("insert into subject_branch_semester_mapping"
						+ "(subjectid,branchid,semesterid) "
						+ "values((select sid from subject_mst "
						+ "where subjectname=?),(select branchid from "
						+ "branch_mst where branchname=?),(select "
						+ "semid from semester_mst where semestername=?))");
		pstmtmap.setString(1, subjectname);
		pstmtmap.setString(2, selectedbranch);
		pstmtmap.setString(3, selectedsemester);
		int recordcountmap = pstmtmap.executeUpdate();
		if(recordCount>0) {
			if(recordcountmap>0) {
				System.out.println("Subject Added");
				return true;
			}
		}
		return false;
	}
	
	public static boolean addsem(String semestername,
			String semesterdesc) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("insert into semester_mst"
				+ "(semestername,semesterdesc) values(?,?)");
		pstmt.setString(1, semestername);
		pstmt.setString(2, semesterdesc);
		int recordCount = pstmt.executeUpdate();
		if(recordCount>0) {
			System.out.println("Semester Added");
			return true;
		}
		return false;
	}
	
	public static boolean addbranch(String branchname,String branchdesc) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("insert into branch_mst"
				+ "(branchname,branchdesc) values(?,?)");
		pstmt.setString(1, branchname);
		pstmt.setString(2, branchdesc);
		int recordCount = pstmt.executeUpdate();
		if(recordCount>0) {
			System.out.println("Branch Added");
			return true;
		}
		return false;
	}
	
	public static boolean addrole(String rolename,String roledesc, 
			String selectedright) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt,pstmtmap = null;
		String regex=",";
	    String[] rights=selectedright.split(regex);
		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("insert into role_mst"
				+ "(rolename,roledesc) values(?,?)");
		pstmt.setString(1, rolename);
		pstmt.setString(2, roledesc);
		int recordCount = pstmt.executeUpdate();
		if(recordCount>0) {
			for(String str : rights)
		    {
				pstmtmap = con.prepareStatement("insert into "
						+ "role_right_mapping(roleid,rightid) "
						+ "values((select roleid from role_mst where "
						+ "rolename=?),(select rightid from right_mst "
						+ "where rightname=?))");
				pstmtmap.setString(1, rolename);
				pstmtmap.setString(2, str.trim());
				int recordcount1 = pstmtmap.executeUpdate();
		    	System.out.println(str.trim());
		    }
			System.out.println("Role Added");
			return true;
		}
		return false;
	}
}
