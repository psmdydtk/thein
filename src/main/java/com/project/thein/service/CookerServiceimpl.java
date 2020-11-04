package com.project.thein.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.dao.CookerDao;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

@Service
public class CookerServiceimpl implements CookerService {
	@Autowired
	private CookerDao dao;

	@Override
	   public List<ShopVO> search(String loc)throws Exception {
	      List<ShopVO> list = new ArrayList<ShopVO>();
	      dao.search(loc);
	      return list;
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
	public UserVO login(UserVO vo){
		return dao.login(vo);
	}

}
