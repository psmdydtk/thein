package com.seoul.thein.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seoul.thein.vo.CrudVO;
import com.seoul.thein.vo.pagingVO;

@Repository
public class CrudDaoImpl implements CrudDao{
	private int n=0;
	
	@Autowired
	private SqlSessionFactory factory;
	
	@Override
	public int insertUser(CrudVO vo) throws Exception {
		try {
			 n= factory.openSession().insert("crud.mapper.insertUser", vo);
			}
			catch(Exception e) {
				n=0;
			}
			System.out.println("회원가입 결과 n=1 success ? false " + n);
			return n;
		
	}

	@Override
	public int updateUser(CrudVO vo) throws Exception {
		// TODO Auto-generated method stub
		n=factory.openSession().update("crud.mapper.updateUser", vo);
		return n;
		
	}

	@Override
	public int deleteUser(CrudVO vo) throws Exception {
		// TODO Auto-generated method stub
		n=factory.openSession().delete("crud.mapper.deleteUser", vo);
		return n;
	}

	@Override
	public List<CrudVO> searchId(CrudVO vo) throws Exception {
		return factory.openSession().selectList("crud.mapper.searchId",vo);
	}

	@Override
	public List<CrudVO> listAll() throws Exception {
		return factory.openSession().selectList("crud.mapper.listUser");
	}
	
	@Override
	public boolean loginUser(CrudVO vo) throws Exception {
		//id 값으로 select count(*) 한다음에 결과가 0이면 false :true 반환 
		String check = factory.openSession().selectOne("crud.mapper.loginUser", vo);
		return (Integer.parseInt(check)==0)?false:true;
	}

	@Override
	public int countList() throws Exception {
		int check = factory.openSession().selectOne("crud.mapper.countList");
		return check;
	}

	@Override
	public List<CrudVO> pagingList(pagingVO pv) throws Exception {
		
		return factory.openSession().selectList("crud.mapper.selectBoard",pv);
	}


}
