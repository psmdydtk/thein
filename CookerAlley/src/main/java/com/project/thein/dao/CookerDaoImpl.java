package com.project.thein.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
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
		int count = factory.openSession().selectOne("cooker.mapper.countList", loc);
		return count;
	}

	@Override
	public UserVO login(UserVO vo) {
		// TODO Auto-generated method stub
		UserVO uvo = factory.openSession().selectOne("cooker.mapper.loginUser", vo);
		return uvo;

	}

	@Override
	public List<ShopVO> searchShop(int shop) throws Exception {
		List<ShopVO> list = factory.openSession().selectList("cooker.mapper.searchShop", shop);
		System.out.println(list);
		return list;
	}

	@Override
	public List<ReservationVO> searchReserTime(ReservationVO rv) throws Exception {
		List<ReservationVO> list = factory.openSession().selectList("cooker.mapper.searchReserTime", rv);
		return list;
	}

	@Override
	public List<KeywordVO> searchKeyword(String shop) throws Exception {
		List<KeywordVO> list = factory.openSession().selectList("cooker.mapper.searchKeyword", shop);
		return list;
	}
}
