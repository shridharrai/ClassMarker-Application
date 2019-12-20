package com.classmarker.admin.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.classmarker.dao.AdminDAO;
import com.classmarker.dao.CommonDAO;

public class Role {
	private String rolename;
	private String roledesc;
	private String showrights;
	private String selectedright;
	private String message;
	private List<String> rightlist = new ArrayList<String>();
	ArrayList<Role> rolelist = new ArrayList<>();
	@Override
	public String toString() {
		return "Role [rolename=" + rolename + ", roledesc=" + roledesc + 
				", showrights=" + showrights
				+ ", selectedright=" + selectedright + ", rightlist=" + 
				rightlist + ", rolelist=" + rolelist + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<Role> getRolelist() {
		return rolelist;
	}
	public void setRolelist(ArrayList<Role> rolelist) {
		this.rolelist = rolelist;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}
	public String getShowrights() {
		return showrights;
	}
	public void setShowrights(String showrights) {
		this.showrights = showrights;
	}
	public String getSelectedright() {
		return selectedright;
	}
	public void setSelectedright(String selectedright) {
		this.selectedright = selectedright;
	}
	public List<String> getRightlist() {
		return rightlist;
	}
	public void setRightlist(List<String> rightlist) {
		this.rightlist = rightlist;
	}
	
	public String fetchroles() throws ClassNotFoundException, SQLException {
		fetchright();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		rolelist = new ArrayList<Role>();
		Role role = new Role();
		
		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select role_mst.rolename,"
				+ "role_mst.roledesc,right_mst.rightname from "
				+ "role_mst,right_mst,role_right_mapping where "
				+ "role_mst.roleid=role_right_mapping.roleid and "
				+ "right_mst.rightid=role_right_mapping.rightid");
		rs = pstmt.executeQuery();
		while(rs.next()) {
			role = new Role();
			role.setRolename(rs.getString(1));
			role.setRoledesc(rs.getString(2));	
			role.setShowrights(rs.getString(3));
			rolelist.add(role);			
		}
		System.out.println(rolelist);
		return "success";
	}

	public String addrole() throws ClassNotFoundException, SQLException {
//		fetchright();
		if(AdminDAO.addrole(rolename, roledesc,selectedright)==true) {
			fetchroles();
			this.message = "Added";
			return "success";
		}
		return "fail";
	}
	
	public String fetchright() throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt,pstmt1 = null;
		ResultSet rs,rs1 = null;		
		con = CommonDAO.getConnection();
		pstmt = con.prepareStatement("select rightname from right_mst");
		rs = pstmt.executeQuery();
		while(rs.next()) {
			rightlist.add(rs.getString(1));
		}
		
		return "success";
	}
}
