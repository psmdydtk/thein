package com.project.thein.service;

import java.util.List;


import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

public interface CookerService {
	public int countList(String loc) throws Exception;
	public List<ShopVO> search(PagingVO pv)throws Exception;
	public String info(String id);
	public UserVO login(UserVO vo);
	public ShopVO shopSearch(ShopVO vo)throws Exception;
	public List<ShopVO> searchShop(String shop)throws Exception;

}
