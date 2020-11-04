package com.project.thein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.dao.CookerDao;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

@Service
public class CookerServiceimpl implements CookerService {
	@Autowired
	private CookerDao dao;

	@Override
    public List<ShopVO> search(PagingVO pv)throws Exception {
       List<ShopVO> list =  dao.search(pv);
       return list;
    }
@Override
 public int countList(String loc) throws Exception {
    int count = dao.countList(loc);
    return count;
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
