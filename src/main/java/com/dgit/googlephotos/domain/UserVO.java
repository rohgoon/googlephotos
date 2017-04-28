package com.dgit.googlephotos.domain;

public class UserVO {
	private int uno;
	private String uid;
	private String uname;
	private String uemail;
	private String uphone;
	private String upassword;
	private String upath;
	
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUpath() {
		return upath;
	}
	public void setUpath(String upath) {
		this.upath = upath;
	}
	@Override
	public String toString() {
		return "UserVO [uno=" + uno + ", uid=" + uid + ", uname=" + uname + ", uemail=" + uemail + ", uphone=" + uphone
				+ ", upassword=" + upassword + ", upath=" + upath + "]";
	}	
	
	
}
