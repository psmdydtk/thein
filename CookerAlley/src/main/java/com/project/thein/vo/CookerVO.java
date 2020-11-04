package com.project.thein.vo;

///TEST - TB_CUSTOMER
public class CookerVO {
	private String name;
	private String password;
	private String id;
	
	public CookerVO() {
		super();
	}

	public CookerVO(String name, String password, String id) {
		super();
		this.name = name;
		this.password = password;
		this.id = id;
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
	
	
}
