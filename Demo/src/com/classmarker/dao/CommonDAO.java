package com.classmarker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface CommonDAO {
	static Connection getConnection() throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		Class.forName(rb.getString("driver"));
		Connection con = null;
		con = DriverManager.getConnection(rb.getString("url"), 
				rb.getString("userid"), rb.getString("password"));
		if (con != null) {
			System.out.println("Connection created...");
		} else {
			System.out.println("Not created...");
		}
		return con;
	}
}
