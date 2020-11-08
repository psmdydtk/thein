package com.project.thein.dao;

import java.util.List;

import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

public interface CookerDao {
	public UserVO login(UserVO vo);
	public List<ShopVO> search(PagingVO pv)throws Exception;
	public int countList(String loc) throws Exception;
	public List<ShopVO> searchShop(int shop)throws Exception;
	public List<ReservationVO> searchReserTime (ReservationVO rv)throws Exception;
	public List<KeywordVO> searchKeyword (String shop) throws Exception;
}
