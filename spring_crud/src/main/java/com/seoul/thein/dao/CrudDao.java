package com.seoul.thein.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.thein.vo.CrudVO;
import com.seoul.thein.vo.pagingVO;

public interface CrudDao {
	
	public int insertUser(CrudVO vo) throws Exception;

	public int updateUser(CrudVO vo) throws Exception;

	public int deleteUser(CrudVO vo) throws Exception;
	
	public boolean loginUser(CrudVO vo) throws Exception;

	public List<CrudVO> searchId(CrudVO vo) throws Exception;

	public List<CrudVO> listAll() throws Exception;

	public int countList() throws Exception;

	public List<CrudVO> pagingList(pagingVO pv) throws Exception;
	
	
}
