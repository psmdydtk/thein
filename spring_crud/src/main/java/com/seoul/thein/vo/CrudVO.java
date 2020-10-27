package com.seoul.thein.vo;

public class CrudVO {
	public CrudVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String name;
	private String password;
	private String id;
	private int RN;
	private int loginStatus;
	public int getRN() {
		return RN;
	}
	public void setRN(int rN) {
		RN = rN;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CrudVO [name=" + name + ", password=" + password + ", id=" + id + "]";
	}
}
