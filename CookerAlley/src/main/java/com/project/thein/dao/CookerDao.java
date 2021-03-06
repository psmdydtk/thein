package com.project.thein.dao;

import java.util.List;

import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
import com.project.thein.vo.ShopOnesVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

public interface CookerDao {
	public UserVO login(UserVO vo);

	public List<ShopVO> search(PagingVO pv) throws Exception;

	public int countList(String loc) throws Exception;

	// public ShopVO shopSearch(ShopVO vo)throws Exception;
	public List<ShopVO> searchShop(ReservationVO rv) throws Exception;

	public List<ReservationVO> searchReserTime(ReservationVO rv) throws Exception;

	public List<KeywordVO> searchKeyword(String shop) throws Exception;

	public int insert(ShopVO vo);

	public UserVO info(String id);

	public int insertReserSuccess(ReservationVO vo) throws Exception;

	public List<ShopVO> getHash() throws Exception;

	public List<ShopVO> heartlist(String id);

	public List<ReservationVO> reservlist(String id);
	
	public int register(UserVO vo) throws Exception;

	public int insertHeart(ShopOnesVO sov);

	public int deleteHeart(ShopOnesVO sov);

	public List<ShopVO> searchShopnoLogin(ReservationVO rv);
}
