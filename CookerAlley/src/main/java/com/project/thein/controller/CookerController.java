package com.project.thein.controller;

import java.net.URI;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.project.thein.encrypt.Encrypt;
import com.project.thein.service.CookerService;
import com.project.thein.vo.KakaoPayReadyVO;
import com.project.thein.vo.KeywordVO;
import com.project.thein.vo.PagingVO;
import com.project.thein.vo.ReservationVO;
import com.project.thein.vo.ShopOnesVO;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;

@Controller
public class CookerController {

	private CookerService service;
	@Autowired
	private Encrypt encrypt;

	public CookerController(CookerService service) {
		super();
		this.service = service;
	}

	// 로그인---------------------------------------------------------------
	/**
	 * 
	 * @param vo
	 * @param session 로그인성공후 세션에 id와 uType(관리자구분용)을 설정한다.
	 * @return ajax처리용 데이터 ajax로 데이터 처리를 하며 , 암호화 후 로그인을 한다.
	 * @throws Exception
	 */
	@RequestMapping("login.do")
	@ResponseBody
	public String loginDo(@ModelAttribute UserVO vo, HttpSession session) throws Exception {
		// 암호화
		String pwd = encrypt.Encrypt(vo.getUser_pwd());
		/*
		 * 복호화 pwd = encrypt.decAES(pwd);
		 * System.out.println("Controller login.do descAES Called  복호화 한 패스워드 : " +
		 * pwd);
		 */
		vo.setUser_pwd(pwd);
		// System.out.println("Controller login.do Called!! login id :
		// "+vo.getUser_id());
		vo = service.login(vo);
		// 로그인실패시
		if (vo != null) {
			if (vo.getUser_id() != null) {
				String type = Integer.toString(vo.getUser_type());
				session.setAttribute("id", vo.getUser_id());
				session.setAttribute("uType", type);
				return "1";
			} else {
				return "0";
			}
		} else {
			return "0";
		}
	}

	// 로그아웃------------------------------------------------------
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:main.do";
	}

	/**
	 * 
	 * @param sido       = 시도
	 * @param loc        = 구군
	 * @param date       = 달력날짜
	 * @param pv         =페이징뷰 VO
	 * @param nowPage    =페이징뷰 매개변수
	 * @param cntPerPage =페이징뷰 매개변수
	 * @param model      데이터 담을 객체
	 * @return pagingvo 객체와 bt_shop테이블 값을 list형식으로 넘겨준다.
	 * @throws Exception 메인페이지에서 시도군과 날짜를 입력받으면 해당 지역군에 있는 가게를 검색해준다.
	 */
	@GetMapping("search.do")
	public String search(@RequestParam("sido1") String sido, @RequestParam("gugun1") String loc,
			@RequestParam("datepick") String date, PagingVO pv,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage, Model model) throws Exception {
		if (loc.equals(" ")) {
			loc = sido;
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
		pv = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), loc);
		model.addAttribute("paging", pv);
		model.addAttribute("viewAll", service.search(pv));
		return "search";
	}

	// 식당 상세정보 찾기-------------------------------------------------
	/**
	 * 
	 * @param sv      : bt_shop VO이다.
	 * @param request : 폼에서 id값을 shop_id_**형식으로 넘어와서 해당처리를 위한 객체
	 * @param model
	 * @param rv
	 * @return bt_shop table 값이 리턴된다.
	 * @throws Exception
	 */
	@RequestMapping("detail.do")
	public String detail(ShopVO sv, HttpServletRequest request, Model model, ReservationVO rv) throws Exception {
		Enumeration enumeration = request.getParameterNames();
		String name = "";
		String user_id="";
		while (enumeration.hasMoreElements()) {
			// name = post한 데이터이다. shop_id_뒤에 어떤값이 올지몰라 이러한 짓을 함.
			String check =(String) enumeration.nextElement();
			if(check.contains("shop_id")) {
				name = check;
				name = name.replace("shop_id_", "");
			}else if(check.contains("reser_user_id_")){
				user_id = check;
				user_id = user_id.replace("reser_user_id_", "");
			}
		}
		rv.setReser_shop_id(name);
		rv.setReser_user_id(user_id);
		List<ShopVO> list = service.searchShop(rv);
		List<KeywordVO> listKeyword = service.searchKeyword(name);
		System.out.println("Controller.detailCalled!! : listkeyword = \n" + listKeyword);
		model.addAttribute("list", list);
		model.addAttribute("keyword", listKeyword);
		return "detail";// 식당정보(?이름?사진?)클릭시 식당의 전체정보 가져와
	}

	// 회원정보 가져오기-------------------------------------------------
	@RequestMapping("UserInfo.do")
	public ModelAndView info(HttpSession session) {
		String id = (String) session.getAttribute("id");
		return new ModelAndView("UserInfo", "info", service.info(id));// id로 회원 전체 정보 가져와
	}

	/**
	 * 서버 내부에 저장된 json파일을 데이터 전처리를 한후 뷰단에 뿌려준다. main 페이지
	 */
	@RequestMapping("main.do")
	public String main(Model md) throws Exception {
		JSONObject crawl = service.insta_crawl();
		md.addAttribute("crawl", crawl);
		return "main";
	}

	/**
	 * 미구현기능 가게등록기능이다.
	 * 
	 * @return
	 */
	@RequestMapping("shopInsert.do")
	public ModelAndView loginPage() {
		return new ModelAndView("shopInsert");
	}

	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception 미구현기능 가게등록 ajax처리
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public String insert(@ModelAttribute ShopVO vo) throws Exception {
		if (service.insert(vo) == 1) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 
	 * @param vo
	 * @param mv
	 * @return 가게 예약페이지
	 */
	@GetMapping("goReser.do")
	public String goReser(ReservationVO vo, Model mv) {
		mv.addAttribute("reservationVO", vo);
		System.out.println("CookerController. goRser Colled  : vo :" + vo);
		return "Reservation";
	}

	/**	가게 예약 하는 기능
	 */
	@RequestMapping("Reservation.do")
	public String reservation(ReservationVO vo) throws Exception {
		// System.out.println(vo);
		InetAddress ip = InetAddress.getLocalHost();
		String localhost="http://";
				localhost+=ip.getHostAddress()+":8080";
				System.out.println(localhost);
		String reser_shop_price = Integer.toString(vo.getReser_shop_price());
		String reser_shop_id = vo.getReser_shop_id();
		String user_id = vo.getReser_user_id();
		String reser_shop_date = vo.getReser_shop_date();
		String reser_shop_regi= vo.getReser_shop_regi();
		int reser_shop_person = vo.getReser_shop_person();
		int reser_shop_hour = vo.getReser_shop_hour();
		KakaoPayReadyVO kakaoPayReadyVO;
		final String HOST = "https://kapi.kakao.com";
		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "f736e0e67209f5282c308d50b67bf198");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "506889");
		params.add("partner_user_id", user_id);
		params.add("item_name", reser_shop_id + "번가게" + reser_shop_date+"일" + reser_shop_hour + "시 예약");// 값가져와서넣기
		params.add("quantity", "1");
		params.add("total_amount", reser_shop_price);// 값가져와서넣기
		params.add("tax_free_amount", "100");
		params.add("approval_url", localhost+"/thein/kakaoPaySuccess.do?reser_user_id="+user_id+"&reser_shop_date="+reser_shop_date
				+"&reser_shop_hour="+reser_shop_hour+"&reser_shop_person="+reser_shop_person+"&reser_shop_price="+reser_shop_price
				+"&reser_shop_id="+reser_shop_id+"&reser_shop_regi="+reser_shop_regi);
		params.add("cancel_url", localhost+"/thein/kakaoPayCancel.do");
		params.add("fail_url", localhost+"/thein/kakaoPaySuccessFail.do");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
		return "redirect:" + kakaoPayReadyVO.getNext_redirect_pc_url();

	}

	/**
	 * 
	 * @param rv
	 * @param request :datepick(선택한날짜) , shop_id(가게 고유키)
	 * @param md
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchTime.do")
	@ResponseBody
	public List<ReservationVO> searchTime(@ModelAttribute ReservationVO rv, HttpServletRequest request, Model md)
			throws Exception {
		String datepick = request.getParameter("datepick");
		String shop_id = request.getParameter("shop_id");
		System.out.println("colled controller.searchTime.do datepick= " + datepick + " shop_id = " + shop_id);
		rv.setReser_shop_id(shop_id);
		rv.setReser_shop_date(datepick);
		List<ReservationVO> result = service.searchReserTime(rv);
		md.addAttribute("list", result);
		return result;
	}

	/**
	 * 해쉬태그별 인스타 크롤링을 하기위한 전처리과정
	 * 
	 */
	@RequestMapping("hashInsta.do")
	public String hashInsta(HttpServletRequest request, Model md) throws Exception {
		String hash = request.getParameter("hash");
		String shop_id = request.getParameter("shop_id");
		JSONObject crawl = service.searchHash(hash);
		md.addAttribute("crawl", crawl);
		return "hashInsta";
	}

	/**
	 * 해쉬태그 데이터 전처리과정
	 */
	@RequestMapping("getHash.do")
	public void getHash() throws Exception {
		service.getHash();
	}

	/**
	 * 
	 * @param SOvo bt_shop_ones테이블 VO
	 * @param Rvo: bt_reservation 테이블 VO 밥상기록 페이지
	 */
	@RequestMapping("UserDiary.do")
	public String goDiary(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		model.addAttribute("heartList", service.heartlist(id));
		model.addAttribute("reservList", service.reservlist(id));

		return "UserDiary";
	}

	/**
	 * 회원가입페이지 이동
	 */
	@GetMapping("goRegister.do")
	public String goRegiser(UserVO vo, Model mv) {
		mv.addAttribute("CookerController :goRegister: UserVO");
		System.out.println("CookerController : goRegister : UserVO :" + vo);
		return "Register";
	}

	/**
	 * userPassword만 암호화 하여 회원가입 실행
	 */
	@RequestMapping("Register.do")
	public void register(@ModelAttribute UserVO vo, HttpServletResponse response) throws Exception {
		// 암호화
		String pwd = encrypt.Encrypt(vo.getUser_pwd());
		vo.setUser_pwd(pwd);

		int result = service.register(vo);
		if (result == 1) {
			// response data로 true를 출력
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	

	@RequestMapping("kakaoPaySuccess.do")
	public String suc(HttpServletResponse response, ReservationVO vo) throws Exception {
		  int result = service.insertReser(vo); 
		  if (result == 1) {
			  return "kakaoPaySuccess";
			  }
		  else {
			  return "kakaoPayCancel";
			}
	}

	@RequestMapping("kakaoPayCancel.do")
	public String fail() {
		return "kakaoPayCancel";
	}

	@RequestMapping("kakaoPaySuccessFail.do")
	public String suFail() {
		return "kakaoPaySuccessFail";
	}
	
	@RequestMapping("insertHeart.do")
	@ResponseBody
	public void insertHeart(ShopOnesVO sov,HttpServletResponse response)throws Exception {
		int result = service.insertHeart(sov);
		System.out.println("called insertHeart result =  " + result );
		if (result == 1) {
			// response data로 true를 출력
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}
	
	@RequestMapping("deleteHeart.do")
	@ResponseBody
	public void deleteHeart(ShopOnesVO sov,HttpServletResponse response)throws Exception {
		int result = service.deleteHeart(sov);
		if (result == 1) {
			// response data로 true를 출력
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}
	///////////////////////// 관리자 페이지----------------식당 수정
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
}
