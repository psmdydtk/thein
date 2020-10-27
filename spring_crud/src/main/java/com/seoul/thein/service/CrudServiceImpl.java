package com.seoul.thein.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.seoul.thein.dao.CrudDao;
import com.seoul.thein.vo.CrudVO;
import com.seoul.thein.vo.pagingVO;

@Service
public class CrudServiceImpl  implements CrudService{
	@Inject
	CrudDao dao;
	

	@Override
	public int insertUser(CrudVO vo) throws Exception {return dao.insertUser(vo);}

	@Override
	public int updateUser(CrudVO vo) throws Exception {return dao.updateUser(vo);}

	@Override
	public int deleteUser(CrudVO vo) throws Exception {return dao.deleteUser(vo);}

	@Override
	public List<CrudVO> searchId(CrudVO vo) throws Exception {return dao.searchId(vo);}

	@Override
	public List<CrudVO> listAll() throws Exception {return dao.listAll();}
	
	/**
	 * @param vo  : id,password
	 * @param session : set id
	 * login success 시 session에 id값 셋팅 
	 */
	@Override
	public boolean loginUser(CrudVO vo, HttpSession session) throws Exception{
		boolean result = dao.loginUser(vo);
		if(result==true) {
			session.setAttribute("id", vo.getId());
		}
		return result;
	}

	@Override
	public int countList() throws Exception{
		int n=dao.countList();
		return n;
	}

	@Override
	public List<CrudVO> pagingList(pagingVO pv) throws Exception {return dao.pagingList(pv);}

}
