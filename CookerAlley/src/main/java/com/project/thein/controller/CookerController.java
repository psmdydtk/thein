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

	/**
	 * 서버 내부에 저장된 json파일을 데이터 전처리를 한후 뷰단에 뿌려준다. main 페이지
	 */
	@RequestMapping("main.do")
	public String main(Model md) throws Exception {
		JSONObject crawl = service.insta_crawl();
		md.addAttribute("crawl", crawl);
		return "main";
	}
	
	// 로그인---------------------------------------------------------------
	/**
	 * @param session 로그인성공후 세션에 id와 uType(관리자구분용)을 설정한다.
	 * @return ajax처리용 데이터 ajax로 데이터 처리를 하며 , 암호화 후 로그인을 한다.
	 * @throws Exception
	 */
	@RequestMapping("login.do")
	@ResponseBody
	public String loginDo(@ModelAttribute UserVO vo, HttpSession session) throws Exception {
		// 암호화
		String pwd = encrypt.Encrypt(vo.getUser_pwd());
		vo.setUser_pwd(pwd);
		vo = service.login(vo);
		//로그인성공시 session에 id와 관리자 판별용 값 설정 (관리자=9999)
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
			return "0";//0일경우 Header.jsp 에서 폼초기화 
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
	 * @param sido : 시도
	 * @param loc  : 군
	 * @param date : main.jsp 달력 선택한 날짜
	 * @param pv   : pagingViewVO
	 * @param nowPage : 현재페이지
	 * @param cntPerPage : 보여줄페이지개수
	 * @param model : 리턴객체
	 * @return search.jsp페이지로 리턴
	 */
	@GetMapping("search.do")
	public String search(@RequestParam("sido1") String sido, @RequestParam("gugun1") String loc,
			@RequestParam("datepick") String date, PagingVO pv,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage, Model model) throws Exception {
		if (loc.equals(" ")) {loc = sido;}//군을 안골랐을시 시도로 대체하여 검색
		int total = service.countList(loc);
		//페이징뷰 셋팅 
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "5";
		}
		//페이징뷰 초기화 및 계산
		pv = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), loc);
		//페이징뷰 와 리스트 데이터 셋팅 및 리턴
		model.addAttribute("paging", pv);
		model.addAttribute("viewAll", service.search(pv));
		return "search";
	}
	//찜목록추가
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
	//찜목록 삭제
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
	// 식당 상세정보 찾기-------------------------------------------------
	/**
	 * @param sv      : bt_shop VO이다.
	 * @param request : 폼에서 id값을 shop_id_**형식으로 넘어와서 해당처리를 위한 객체
	 * @param model
	 * @param rv : bt_reservation VO이다.
	 * @return bt_shop 와 bt_keyword 값이 리턴된다.
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
		rv.setReser_shop_id(name);rv.setReser_user_id(user_id);
		List<ShopVO> list = service.searchShop(rv);
		List<KeywordVO> listKeyword = service.searchKeyword(name);
		System.out.println("Controller.detailCalled!! : listkeyword = \n" + listKeyword);
		model.addAttribute("list", list);
		model.addAttribute("keyword", listKeyword);
		return "detail";// 식당정보(?이름?사진?)클릭시 식당의 전체정보 가져와
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
	/**	가게 예약 하는 기능
	 */
	@RequestMapping("Reservation.do")
	public String reservation(ReservationVO vo) throws Exception {
		String reser_shop_price = Integer.toString(vo.getReser_shop_price());//가게예약금
		String reser_shop_id = vo.getReser_shop_id();//예약 가게 id
		String user_id = vo.getReser_user_id(); //user Id
		String reser_shop_date = vo.getReser_shop_date(); // 예약날짜
		String reser_shop_regi= vo.getReser_shop_regi(); //등록날짜
		int reser_shop_person = vo.getReser_shop_person(); //예약인원
		int reser_shop_hour = vo.getReser_shop_hour(); //예약시간
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
		params.add("approval_url", "http://localhost:8080/thein/kakaoPaySuccess.do?reser_user_id="+user_id+"&reser_shop_date="+reser_shop_date
				+"&reser_shop_hour="+reser_shop_hour+"&reser_shop_person="+reser_shop_person+"&reser_shop_price="+reser_shop_price
				+"&reser_shop_id="+reser_shop_id+"&reser_shop_regi="+reser_shop_regi);
		params.add("cancel_url", "http://localhost:8080/thein/kakaoPayCancel.do");
		params.add("fail_url", "http://localhost:8080/thein/kakaoPaySuccessFail.do");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
		return "redirect:" + kakaoPayReadyVO.getNext_redirect_pc_url();
	}
	//카카오페이 결제 성공시 
	@RequestMapping("kakaoPaySuccess.do")
	public String suc(HttpServletResponse response, ReservationVO vo) throws Exception {
		  int check = service.insertReser(vo); 
		 String result= (check==1)?"kakaoPaySuccess":"kakaoPayCancel";
		 return result;
	}
	//카카오페이 결제 취소시
	@RequestMapping("kakaoPayCancel.do")
	public String fail() {
		return "kakaoPayCancel";
	}
	//카카오페이 결제 실패시
	@RequestMapping("kakaoPaySuccessFail.do")
	public String suFail() {
		return "kakaoPaySuccessFail";
	}
	//회원가입페이지 이동
	@GetMapping("goRegister.do")
	public String goRegiser(UserVO vo, Model mv) {
		mv.addAttribute("CookerController :goRegister: UserVO");
		System.out.println("CookerController : goRegister : UserVO :" + vo);
		return "Register";
	}
	//회원등록 사이트 이동
	@RequestMapping("Register.do")
	public void register(@ModelAttribute UserVO vo, HttpServletResponse response) throws Exception {
		// 암호화
		String pwd = encrypt.Encrypt(vo.getUser_pwd());
		vo.setUser_pwd(pwd);
		int check = service.register(vo);
		boolean result = (check==1)?true:false;
		response.getWriter().print(result);
	}
	// 회원정보 가져오기-------------------------------------------------
	@RequestMapping("UserInfo.do")
	public ModelAndView info(HttpSession session) {
		String id = (String) session.getAttribute("id");
		return new ModelAndView("UserInfo", "info", service.info(id));// id로 회원 전체 정보 가져와
	}
	/**
	 * 미구현기능 가게등록기능이다.
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
		String result = (service.insert(vo) == 1)?"1":"0";
		return result;
	}
	/**
	 * @param SOvo: bt_shop_ones테이블 VO
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
	 * 해쉬태그별 인스타 크롤링을 하기위한 전처리과정
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
