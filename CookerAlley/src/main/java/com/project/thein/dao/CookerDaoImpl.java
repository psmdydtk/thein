package com.project.thein.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
import com.project.thein.vo.ShopOnesVO;
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

	/*
	 * @Override public ShopVO shopSearch(ShopVO vo) throws Exception { //ShopVO
	 * list=factory.openSession().selectOne("cooker.mapper.shopSearch",vo); return
	 * factory.openSession().selectOne("cooker.mapper.shopSearch",vo); }
	 */

	@Override
	public List<ShopVO> searchShop(ReservationVO rv) throws Exception {
		List<ShopVO> list = factory.openSession().selectList("cooker.mapper.searchShop", rv);
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

	@Override
	public int insert(ShopVO vo) {
		// TODO Auto-generated method stub
		int result = factory.openSession().insert("cooker.mapper.insertShop", vo);
		return result;

	}

	@Override
	public UserVO info(String id) {
		// TODO Auto-generated method stub
		UserVO uvo = factory.openSession().selectOne("cooker.mapper.Userinfo", id);
		return uvo;
	}

	// 예약
	@Override
	public int insertReserSuccess(ReservationVO vo) throws Exception {
		int insertDao = factory.openSession().insert("cooker.mapper.reservationInsert", vo);
		return insertDao;
	}

	@Override
	public List<ShopVO> getHash() throws Exception {
		List<ShopVO> list = factory.openSession().selectList("cooker.mapper.selectHash");
		return list;
	}

	@Override
	public List<ShopVO> heartlist(String id) {
		// TODO Auto-generated method stub
		List<ShopOnesVO> list = factory.openSession().selectList("cooker.mapper.heartlist", id);
		List<ShopVO> result = new ArrayList<ShopVO>();
		for(int i=0; i<list.size(); i++) {
			List<ShopVO> cast = factory.openSession().selectList("cooker.mapper.getHeart", list.get(i).getOnes_shop_id());
			result.add(cast.get(0));
		}
		return result;
	}

	@Override
	public List<ReservationVO> reservlist(String id) {
		// TODO Auto-generated method stub
		return factory.openSession().selectList("cooker.mapper.reservlist", id);
	}
	//회원가입
	   @Override
	   public int register(UserVO vo) throws Exception {
	      int registerSuccess = factory.openSession().insert("cooker.mapper.register", vo);
	      return registerSuccess;
	   }

	@Override
	public int insertHeart(ShopOnesVO sov) {
		int result = factory.openSession().insert("cooker.mapper.insertHeart", sov);
		return result;
	}

	@Override
	public int deleteHeart(ShopOnesVO sov) {
		int result = factory.openSession().insert("cooker.mapper.deleteHeart", sov);
		return result;
	}

	@Override
	public List<ShopVO> searchShopnoLogin(ReservationVO rv) {
		List<ShopVO> list = factory.openSession().selectList("cooker.mapper.searchShopnoLogin", rv);
		System.out.println(list);
		return list;
	}
}
