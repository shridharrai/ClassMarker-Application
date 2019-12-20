package com.classmarker.dto;

public class RightsDTO {
	private String rightname;
	private String screenname;
	@Override
	public String toString() {
		return "RightsDTO [rightname=" + rightname + ", screenname=" + screenname + "]";
	}
	public String getRightname() {
		return rightname;
	}
	public void setRightname(String rightname) {
		this.rightname = rightname;
	}
	public String getScreenname() {
		return screenname;
	}
	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}
	public RightsDTO() {
		
	}
	public RightsDTO(String rightname, String screenname) {
		super();
		this.rightname = rightname;
		this.screenname = screenname;
	}
}
