package com.project.thein.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
import com.project.thein.vo.ShopOnesVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

public interface CookerService {
	public int countList(String loc) throws Exception;

	public List<ShopVO> search(PagingVO pv) throws Exception;

	public UserVO info(String id);

	public UserVO login(UserVO vo);

	// public ShopVO shopSearch(ShopVO vo)throws Exception;
	public List<ShopVO> searchShop(int shop) throws Exception;

	public List<ReservationVO> searchReserTime(ReservationVO rv) throws Exception;

	public List<KeywordVO> searchKeyword(String shop) throws Exception;

	public int insert(ShopVO vo);

	public int insertReser(ReservationVO vo) throws Exception;

	public JSONObject insta_crawl() throws Exception;

	public JSONObject searchHash(String hash) throws Exception;

	public void getHash() throws Exception;

	public List<ShopOnesVO> heartlist(String id);

	public List<ReservationVO> reservlist(String id);
	
	public int register(UserVO vo) throws Exception;
	

}
