package com.project.thein.controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.thein.encrypt.Encrypt;
import com.project.thein.service.CookerService;
import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;


@Controller
public class CookerController{
	private CookerService service;
	@Autowired
	private Encrypt encrypt;
	
	public CookerController(CookerService service) {
		super();
		this.service = service;
	}
	
	//로그인---------------------------------------------------------------
	@RequestMapping("login.do")
	@ResponseBody
	public String loginDo(@ModelAttribute UserVO vo,HttpSession session) throws Exception{
		//암호화
		String pwd = encrypt.Encrypt(vo.getUser_pwd());
		System.out.println("1"+pwd);
		//복호화
		pwd=encrypt.decAES(pwd);
		System.out.println("2"+pwd);
		vo.setUser_pwd(pwd);
		
		vo=service.login(vo);
		if(vo.getUser_id()!=null) {
			String type= Integer.toString(vo.getUser_type());
			session.setAttribute("id",vo.getUser_id());
			session.setAttribute("uType", type);
			
			return "1";
		}else {
			return "0";
		}
		//return (crudService.login(vo)==1)?"1":"0";
	}
	//로그아웃------------------------------------------------------
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:main.jsp";
	}
		
	// 검색입력받아 식당 리스트 뽑기-------------------------------------------------
	   @GetMapping("search.do")
	   public String search(@RequestParam("sido1") String sido,@RequestParam("gugun1") String loc, @RequestParam("datepick") String date, PagingVO pv,
	         @RequestParam(value = "nowPage", required = false) String nowPage,
	         @RequestParam(value = "cntPerPage", required = false) String cntPerPage, Model model) throws Exception {
	      if(loc.equals(" ")) {
	         System.out.println(loc+"찍히니?");
	         loc=sido;
	         System.out.println(loc);
	      }
	      int total = service.countList(loc);
	      if (nowPage == null && cntPerPage == null) {
	         nowPage = "1";
	         cntPerPage = "5";
	      } else if (nowPage == null) {
	         nowPage = "1";
	      } else if (cntPerPage == null) {
	         cntPerPage = "5";
	      }
	      pv = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage),loc);
	      model.addAttribute("paging", pv);
	      model.addAttribute("viewAll", service.search(pv));
	      return "search";
	   }

	   // 식당 상세정보 찾기-------------------------------------------------
	   @RequestMapping("detail.do")
	   public String detail(ShopVO sv, HttpServletRequest request,Model model,ReservationVO rv)throws Exception {
	      //폼데이터가 뭐로 받아오는지 몰라서 해당방식으로 데이터를 찾음
		   String reser_shop_id = request.getParameter("shop_id");
		   rv.setReser_shop_id(reser_shop_id);
		   service.searchReserTime(rv);
		   
	      Enumeration enumeration=request.getParameterNames();
	      String name="";
	      while(enumeration.hasMoreElements()) {
	         //name = post한 데이터이다. shop_id_뒤에 어떤값이 올지몰라 이러한 짓을 함.
	         name=(String)enumeration.nextElement();
	         name=name.replace("shop_id_", "");
	      }
	      int changeName = Integer.parseInt(name);
	      List<ShopVO> list = service.searchShop(changeName);
	      List<KeywordVO> listKeyword = service.searchKeyword(name);
	      System.out.println(listKeyword);
	      model.addAttribute("list",list);
	      model.addAttribute("keyword",listKeyword);
	      return "detail";// 식당정보(?이름?사진?)클릭시 식당의 전체정보 가져와
	   }
	//회원정보 가져오기-------------------------------------------------
	@RequestMapping("UserInfo.do")
	public ModelAndView info(HttpSession session) {
		//String result = service.toString();
		String id=(String)session.getAttribute("id");
		return new ModelAndView("UserInfo","info",service.info(id));//id로 회원 전체 정보 가져와
	}
	/////////////////////////관리자 페이지----------------식당 수정
	/*
	 * @GetMapping("shopUpdate.do") public String
	 * shopSearch(@RequestParam("shop_id") String shop_id, @ModelAttribute ShopVO
	 * vo,Model model) throws Exception{ //List<ShopVO> list =
	 * service.shopSearch(vo); //List<ShopVO> list=service.shopSearch(shop_id);
	 * System.out.println(vo.getShop_id()); vo.setShop_id(shop_id);
	 * System.out.println(vo.getShop_id()); ModelAndView mmm = new
	 * ModelAndView("MgtSearchShop","list",service.shopSearch(vo));
	 * model.addAttribute("list", service.shopSearch(vo)); return "shopUpdate"; }
	 */
	
	@RequestMapping("main.do")
	public String main(Model md)throws Exception{
		// filereader한 객체를 오브젝트에 담고 jsonparser 로 파싱한다.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("/instagram/crawling.json"));
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
		md.addAttribute("json",returnChangeJson);
		
		return "main";
	}
	@RequestMapping("reservation.do")
	@ResponseBody
	public String reservation(@ModelAttribute ReservationVO rv,Model model) throws Exception{
		System.out.println("들어오긴하니");
		List<ReservationVO> list = service.searchReserTime(rv);
		
		model.addAttribute("reser",list);
		return "ajaxExam";
	}
	@RequestMapping("go.do")
	public String go()throws Exception{
		return "go";
	}
	@RequestMapping("goreser")
	public String goreser()throws Exception{
		return "ajaxExam";
	}

}
