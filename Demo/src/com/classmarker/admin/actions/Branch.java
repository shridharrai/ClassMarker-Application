package com.classmarker.admin.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.classmarker.dao.AdminDAO;
import com.classmarker.dao.CommonDAO;

public class Branch {
	private String branchname;
	private String branchdesc;
	private String message;
	ArrayList<Branch> branchlist = new ArrayList<>();
	@Override
	public String toString() {
		return "Branch [branchname=" + branchname + ", branchdesc=" + 
	branchdesc + ", branchlist=" + branchlist + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<Branch> getBranchlist() {
		return branchlist;
	}
	public void setBranchlist(ArrayList<Branch> branchlist) {
		this.branchlist = branchlist;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getBranchdesc() {
		return branchdesc;
	}
	public void setBranchdesc(String branchdesc) {
		this.branchdesc = branchdesc;
	}
	
	public String addbranch() throws ClassNotFoundException, SQLException {
		if(AdminDAO.addbranch(branchname, branchdesc)==true) {
			execute();
			this.message = "Added";
			return "success";
		}
		return "fail";
	}
	
	public String execute() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		branchlist = new ArrayList<Branch>();
		Branch branch;
		
		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select branchname,branchdesc "
				+ "from branch_mst where status=\"Y\"");
		rs = pstmt.executeQuery();
		while(rs.next()) {
			branch = new Branch();
			branch.setBranchname(rs.getString(1));
			branch.setBranchdesc(rs.getString(2));	
			branchlist.add(branch);			
		}
		System.out.println(branchlist);
		return "success";
	}
}
