package com.project.thein.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thein.dao.CookerDao;
import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
import com.project.thein.vo.ShopOnesVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

@Service
public class CookerServiceimpl implements CookerService {
	@Autowired
	private CookerDao dao;

	@Override
	public List<ShopVO> search(PagingVO pv) throws Exception {
		List<ShopVO> list = dao.search(pv);
		return list;
	}

	@Override
	public int countList(String loc) throws Exception {
		int count = dao.countList(loc);
		return count;
	}

	@Override
	public UserVO info(String id) {
		// TODO Auto-generated method stub
		return dao.info(id);
	}

	@Override
	public UserVO login(UserVO vo) {
		return dao.login(vo);
	}

	/*
	 * @Override public ShopVO shopSearch(ShopVO vo) throws Exception { // TODO
	 * Auto-generated method stub return dao.shopSearch(vo); }
	 */
	@Override
	public List<ShopVO> searchShop(int shop) throws Exception {
		List<ShopVO> list = dao.searchShop(shop);
		return list;
	}

	@Override
	public List<ReservationVO> searchReserTime(ReservationVO rv) throws Exception {
		List<ReservationVO> list = dao.searchReserTime(rv);
		return list;
	}

	@Override
	public List<KeywordVO> searchKeyword(String shop) throws Exception {
		List<KeywordVO> list = dao.searchKeyword(shop);
		return list;
	}

	@Override
	public int insert(ShopVO vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	@Override
	public int insertReser(ReservationVO vo) throws Exception {
		int insertReserSuccess = dao.insertReserSuccess(vo);
		if (insertReserSuccess != 1) {
			System.out.println("CookerServiceImpl : insertReser : 예약 실패");
		} else {
			System.out.println("CookerServiceImpl : insertReser : 예약 성공");
		}
		return insertReserSuccess;
	}

	/**
	 * 외부 Python파일에서 인스타그램 크롤링을 해온다음 파일을 읽어와서
	 * 파싱하고 해쉬태그 3개만 들어가게 전처리하는 과정
	 */
	public JSONObject insta_crawl() throws Exception {
		JSONParser parser = new JSONParser();
		//Object obj = parser.parse(new FileReader("/home/ghkdldjtjd/git/thein/instagram/crawling.json")); //서버용
		Object obj = parser.parse(new FileReader("C:/instagram/crawling.json")); //pc용
		// 파싱한 객체를 jsonobject 에 담는다
		JSONObject json = (JSONObject) obj;
		// json데이터를 꺼내오기위한 iterator
		Iterator it = json.keySet().iterator();
		// 꺼내온 데이터를 jsonarray로 저장
		JSONArray ja = new JSONArray();
		// 키값으로 사용
		String key;
		// 리턴할 데이터로 해쉬태그3개까지의값만을 가지고 리턴할 객체이다.

		JSONObject returnChangeJson = new JSONObject();
		// iterator를 이용하여 데이터꺼내서 JSONArray객체에 담는 작업
		int count = 0;
		while (it.hasNext()) {
			key = (String) it.next();
			ja.add(json.get(key));
			count++;
		}
		for (int i = 0; i < ja.size(); i++) {
			JSONArray returnJsonArray = new JSONArray();
			JSONObject changeJo = new JSONObject();
			JSONObject changeJson = new JSONObject();
			changeJo = (JSONObject) ja.get(i);
			// System.out.println(changeJo);
			// System.out.println("뽑기src + " + changeJo.get("src"));
			changeJson.put("src", changeJo.get("src"));
			// System.out.println("뽑기tags " + changeJo.get("tags"));
			JSONArray jary = (JSONArray) changeJo.get("tags");

			List list = new ArrayList();
			for (int j = 0; j < jary.size(); j++) {
				if (j == 3) {
					break;
				}
				list.add(jary.get(j));
			}
			returnJsonArray.add(list);
			changeJson.put("tags", returnJsonArray);
			returnChangeJson.put("key" + i, changeJson);
		}
		// 최종결과물
		System.out.println(returnChangeJson);
		return returnChangeJson;// returnJsonArray;

	}
	/**
	 * datail페이지 내 해시태그를 보여주는 곳에서 해시태그를 클릭하면
	 * 해시태그별 해쉬태그.json파일을 읽어와 데이터 전처리하는 과정
	 */
	@Override
	public JSONObject searchHash(String hash) throws Exception {
		JSONParser parser = new JSONParser();
		//Object obj = parser.parse(new FileReader("/home/ghkdldjtjd/git/thein/instagram/" + hash + ".json"));
		 Object obj = parser.parse(new FileReader("C:/instagram/crawling.json"));
		// 파싱한 객체를 jsonobject 에 담는다
		JSONObject json = (JSONObject) obj;
		// json데이터를 꺼내오기위한 iterator
		Iterator it = json.keySet().iterator();
		// 꺼내온 데이터를 jsonarray로 저장
		JSONArray ja = new JSONArray();
		// 키값으로 사용
		String key;
		// 리턴할 데이터로 해쉬태그3개까지의값만을 가지고 리턴할 객체이다.

		JSONObject returnChangeJson = new JSONObject();
		// iterator를 이용하여 데이터꺼내서 JSONArray객체에 담는 작업
		int count = 0;
		while (it.hasNext()) {
			key = (String) it.next();
			ja.add(json.get(key));
			count++;
		}
		for (int i = 0; i < ja.size(); i++) {
			JSONArray returnJsonArray = new JSONArray();
			JSONObject changeJo = new JSONObject();
			JSONObject changeJson = new JSONObject();
			changeJo = (JSONObject) ja.get(i);
			// System.out.println(changeJo);
			// System.out.println("뽑기src + " + changeJo.get("src"));
			changeJson.put("src", changeJo.get("src"));
			// System.out.println("뽑기tags " + changeJo.get("tags"));
			JSONArray jary = (JSONArray) changeJo.get("tags");

			List list = new ArrayList();
			for (int j = 0; j < jary.size(); j++) {
				if (j == 3) {
					break;
				}
				list.add(jary.get(j));
			}
			returnJsonArray.add(list);
			changeJson.put("tags", returnJsonArray);
			returnChangeJson.put("key" + i, changeJson);
		}
		// 최종결과물
		System.out.println(returnChangeJson);
		return returnChangeJson;// returnJsonArray;
	}

	/**
	 * 해시태그 별 크롤링 하기위한 데이터 선별과정
	 */
	@Override
	public void getHash() throws Exception {
		List<ShopVO> list = dao.getHash();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			result.add(list.get(i).getShop_hash1());
			result.add(list.get(i).getShop_hash2());
			result.add(list.get(i).getShop_hash3());
		}
		result = getDistinctLogic(result);
		System.out.println("colled:CookerServiceImpl.getHash()");
		System.out.println(result);

	}

	/**
	 * List에 데이터를 확인하여 중복 제거
	 */
	public List<String> getDistinctLogic(List<String> originList) {

		System.out.println("getDistinctLogic");

		List<String> resultList = new ArrayList<String>();

		int originSize = originList.size();

		for (int a = 0; a < originSize; a++) {

			if (!resultList.contains(originList.get(a))) {

				resultList.add(originList.get(a));
			}
		}

		return resultList;
	}
	//찜목록
	@Override
	public List<ShopOnesVO> heartlist(String id) {
		// TODO Auto-generated method stub
		return dao.heartlist(id);
	}
	//예약목록
	@Override
	public List<ReservationVO> reservlist(String id) {
		// TODO Auto-generated method stub
		return dao.reservlist(id);
	}
	//회원가입
	   @Override
	   public int register(UserVO vo) throws Exception{
	      int registerSuccess = dao.register(vo);
	      
	      if(registerSuccess!=1) {
	         System.out.println("CookerServiceImpl : register : 가입 실패");
	      }else {
	         System.out.println("CookerServiceImpl : register : 가입 성공");
	      }
	      return registerSuccess;
	   }
}
