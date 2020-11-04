package com.project.thein.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.vo.CookerVO;

@Service
public class CookerDaoImpl implements CookerDao {
	@Autowired
	private SqlSessionFactory factory;

	@Override
	public int login(CookerVO vo){
		// TODO Auto-generated method stub
		return factory.openSession().selectOne("cooker.mapper.loginUser", vo);
	}

}
