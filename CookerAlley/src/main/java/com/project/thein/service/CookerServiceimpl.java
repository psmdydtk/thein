package com.project.thein.service;

import org.springframework.stereotype.Service;

@Service
public class CookerServiceimpl implements CookerService {

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

}
