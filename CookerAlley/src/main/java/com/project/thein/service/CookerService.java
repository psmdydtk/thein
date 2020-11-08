package com.project.thein.service;

import java.util.List;

import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

public interface CookerService {
	public int countList(String loc) throws Exception;
	public List<ShopVO> search(PagingVO pv)throws Exception;
	public String info(String id);
	public UserVO login(UserVO vo);
	//public ShopVO shopSearch(ShopVO vo)throws Exception;
	public List<ShopVO> searchShop(int shop)throws Exception;
	public List<ReservationVO> searchReserTime(ReservationVO rv)throws Exception;
	public List<KeywordVO> searchKeyword(String shop)throws Exception;
	
}
