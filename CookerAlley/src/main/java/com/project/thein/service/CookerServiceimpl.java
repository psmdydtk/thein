package com.project.thein.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.dao.CookerDao;
import com.project.thein.vo.CookerVO;

@Service
public class CookerServiceimpl implements CookerService {
	@Autowired
	private CookerDao dao;

	@Override
	public String search(String loc, String date) {
		// TODO Auto-generated method stub
		return "위치 : "+loc+"\n날짜 : "+date;
	}

	@Override
	public String detail(String cooker) {
		// TODO Auto-generated method stub
		return "식당 : "+cooker;
	}

	@Override
	public String info(String id) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public int login(CookerVO vo){
		return dao.login(vo);
	}

}
