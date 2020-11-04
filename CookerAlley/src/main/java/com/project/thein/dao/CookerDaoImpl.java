package com.project.thein.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

@Service
public class CookerDaoImpl implements CookerDao {
	@Autowired
	private SqlSessionFactory factory;
	
	@Override
    public List<ShopVO> search(PagingVO vo) throws Exception {
       List<ShopVO> list = factory.openSession().selectList("cooker.mapper.search", vo);
       return list;
    }

 @Override
 public int countList(String loc) throws Exception {
    int count = factory.openSession().selectOne("cooker.mapper.countList",loc);
    return count;
 }

	@Override
	public UserVO login(UserVO vo){
		// TODO Auto-generated method stub
		UserVO uvo = factory.openSession().selectOne("cooker.mapper.loginUser", vo);
		return uvo;
		
	}

}
