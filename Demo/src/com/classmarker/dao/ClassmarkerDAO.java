package com.classmarker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface ClassmarkerDAO {
	public static boolean startupstatus() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select status from "
				+ "app_startup where user_time=\"First Time\" and status =?");
		pstmt.setString(1, "Y");
		rs = pstmt.executeQuery();
		if(rs.next()) {
			
				System.out.println("Status Y is checked");
				return true;
		}
		rs.close();
		pstmt.close();
		con.close();
		return false;
	}
	
	public static boolean firstlogin(String userid,String password) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select userid,password "
				+ "from user_mst where userid=? and password=?");
		pstmt.setString(1, userid);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			
				System.out.println("userid check");
				return true;
		}
		rs.close();
		pstmt.close();
		con.close();
		return false;
		}
	
	public static boolean firstupdatepwd(String userid,String contact,
			String email,String pwd) throws ClassNotFoundException, SQLException {
		try {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = CommonDAO.getConnection();
		System.out.println("Before Running update Statement");
		pstmt = con.prepareStatement(" update user_mst set userid=?, "
				+ "contact=?, email=? , password=? where uid=1");
		pstmt.setString(1,userid);
		pstmt.setString(2, contact);
		pstmt.setString(3, email);
		pstmt.setString(4,pwd);
		int recordcount = pstmt.executeUpdate();
		System.out.println("After executing update statement");
		pstmt.close();
		con.close();
		return true;
		}catch(Exception e) {
			System.out.println("Got an exception "+e.getMessage());
		
		return false;
	}
	}
	
	public static boolean startupfinish() throws ClassNotFoundException, SQLException {
		try {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("update app_startup set status=? "
				+ "where user_time=\"First Time\"");
		pstmt.setString(1, "N");
		int recordCount = pstmt.executeUpdate();
			System.out.println("Status N is checked");
		pstmt.close();
		con.close();
		return true;
		}catch(Exception e) {
			System.out.println("Got an exception "+e.getMessage());
			return false;
			}
		}
	
	public static boolean login(String userid, String password) throws ClassNotFoundException, SQLException, NamingException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("Userid "+userid+" password "+password);
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select userid,password "
				+ "from user_mst where userid=? and password=?");
		pstmt.setString(1, userid);
		pstmt.setString(2, password);
		rs = pstmt.executeQuery();
		System.out.println(pstmt);
		System.out.println(rs);
		if(rs.next()){
			 return true;
		 }
		 return false;
	}
	
}
