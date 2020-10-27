package com.seoul.thein.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.seoul.thein.vo.CrudVO;
import com.seoul.thein.vo.pagingVO;

public interface CrudService {
	// 게시물 총 갯수
	public int countList() throws Exception;

	// 페이징 처리 게시글 조회
	public List<CrudVO> pagingList(pagingVO vo) throws Exception;
	
	public int insertUser(CrudVO vo) throws Exception;

	public int updateUser(CrudVO vo) throws Exception;

	public int deleteUser(CrudVO vo) throws Exception;
	
	public boolean loginUser(CrudVO vo, HttpSession session)throws Exception;

	public List<CrudVO> searchId(CrudVO vo) throws Exception;

	public List<CrudVO> listAll() throws Exception;	
}
