package com.project.thein.dao;

import java.util.List;

import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

public interface CookerDao {
	public UserVO login(UserVO vo);
	public List<ShopVO> search(String loc)throws Exception;
}
