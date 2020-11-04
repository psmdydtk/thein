package com.project.thein.vo;

public class UserVO {
	private String user_id;
	private int user_type;
	private String user_pwd;
	private String user_name;
	private String user_phone;
	private String user_email;
	private String user_sns_id;
	public UserVO() {
		super();
	}
	public UserVO(String user_id, int user_type, String user_pwd, String user_name, String user_phone,
			String user_email, String user_sns_id) {
		super();
		this.user_id = user_id;
		this.user_type = user_type;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_sns_id = user_sns_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_sns_id() {
		return user_sns_id;
	}
	public void setUser_sns_id(String user_sns_id) {
		this.user_sns_id = user_sns_id;
	}
	
	
}
