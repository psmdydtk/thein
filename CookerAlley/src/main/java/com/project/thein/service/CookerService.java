package com.project.thein.service;

import com.project.thein.vo.CookerVO;

public interface CookerService {
	public String search(String loc,String date);
	public String detail(String cooker);
	public String info(String id);
	public int login(CookerVO vo);

}
