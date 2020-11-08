package com.project.thein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.dao.CookerDao;
import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
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
	public String info(String id) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public UserVO login(UserVO vo){
		return dao.login(vo);
	}
	@Override
	   public List<ShopVO> searchShop(int shop) throws Exception {
	      List<ShopVO> list = dao.searchShop(shop);
	      return list;
	   }
	@Override
	public List<ReservationVO> searchReserTime(ReservationVO rv) throws Exception {
		List<ReservationVO> list = dao.searchReserTime(rv);
		return list;
	}
	@Override
	public List<KeywordVO> searchKeyword(String shop) throws Exception {
		List<KeywordVO> list = dao.searchKeyword(shop);
		return list;
	}
}
