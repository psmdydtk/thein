package com.project.thein.dao;

import java.util.List;

import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

public interface CookerDao {
	public UserVO login(UserVO vo);
	public List<ShopVO> search(PagingVO pv)throws Exception;
	public int countList(String loc) throws Exception;
	public ShopVO shopSearch(ShopVO vo)throws Exception;
	public List<ShopVO> searchShop(String shop)throws Exception;
}
