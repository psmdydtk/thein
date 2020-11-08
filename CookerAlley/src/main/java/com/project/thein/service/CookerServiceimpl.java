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
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

@Service
public class CookerServiceimpl implements CookerService {
	@Autowired
	private CookerDao dao;

	@Override
    public List<ShopVO> search(PagingVO pv)throws Exception {
       List<ShopVO> list =  dao.search(pv);
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
	public UserVO login(UserVO vo){
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
	      if(insertReserSuccess!=1) {
	         System.out.println("CookerServiceImpl : insertReser : 예약 실패");
	      }else {
	         System.out.println("CookerServiceImpl : insertReser : 예약 성공");
	      }
	      return insertReserSuccess;
	   }
	public JSONObject insta_crawl() throws Exception {
        JSONParser parser = new JSONParser();
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
            //System.out.println("뽑기src + " + changeJo.get("src"));
            changeJson.put("src", changeJo.get("src"));
            //System.out.println("뽑기tags " + changeJo.get("tags"));
            JSONArray jary = (JSONArray) changeJo.get("tags");
            
            List list = new ArrayList();
            for (int j = 0; j < jary.size(); j++) {
               if(j==3) {
                  break;
               }
               list.add(jary.get(j));
            }
            returnJsonArray.add(list);
            changeJson.put("tags", returnJsonArray);
            returnChangeJson.put("key"+i, changeJson);
         }
         // 최종결과물
          System.out.println(returnChangeJson);
         return returnChangeJson;// returnJsonArray;

      
   }
}
