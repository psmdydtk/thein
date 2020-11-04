package com.project.thein.service;

import java.util.List;

import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

public interface CookerService {
	public List<ShopVO> search(String loc)throws Exception;
	public String detail(String cooker);
	public String info(String id);
	public UserVO login(UserVO vo);

}
