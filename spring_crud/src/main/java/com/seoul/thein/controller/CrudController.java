package com.seoul.thein.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seoul.thein.service.CrudService;
import com.seoul.thein.vo.CrudVO;
import com.seoul.thein.vo.pagingVO;

@Controller
public class CrudController {
	private CrudService svc;

	public CrudController(CrudService svc) {
		super();
		this.svc = svc;
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LoggerFactory.getLogger(CrudController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate );
	 * 
	 * return "home"; }
	 */
	// 메인화면
	@GetMapping("home")
	public String home(pagingVO vo,@RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage,
			Model model) throws Exception {
		int total = svc.countList();
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		vo = new pagingVO(total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
		model.addAttribute("paging",vo);
		model.addAttribute("viewAll", svc.pagingList(vo));
		
		return "home";
	}

	// 메인화면
	@GetMapping("/")
	public String goHome(pagingVO vo,@RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage,
			Model model) throws Exception {
		int total = svc.countList();
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		vo = new pagingVO(total,Integer.parseInt(nowPage),Integer.parseInt(cntPerPage));
		model.addAttribute("paging",vo);
		model.addAttribute("viewAll", svc.pagingList(vo));
		
		return "home";
	}

	// ID검색기능
	@RequestMapping("searchId.do")
	@ResponseBody
	public List<CrudVO> searchDo(@ModelAttribute CrudVO vo) throws Exception {
		List<CrudVO> list = svc.searchId(vo);
		return list;
	}

	// 회원가입
	@RequestMapping("insert.do")
	public void insertDo(@ModelAttribute CrudVO vo, HttpServletResponse response) throws Exception {
		int n = svc.insertUser(vo);
		if (n == 1) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	// 로그인
	@RequestMapping("login.do")
	public void loginDo(CrudVO vo, HttpServletResponse response, HttpSession session) throws Exception {
		boolean check = svc.loginUser(vo, session);
		if (check == true) {
			// response data로 true를 출력
			response.getWriter().print(true);
		} else if (check == false) {
			response.getWriter().print(false);
		}
	}

	// 회원탈퇴
	@RequestMapping("delete.do")
	public void deleteDo(@ModelAttribute CrudVO vo, HttpServletResponse response) throws Exception {
		int n = svc.deleteUser(vo);
		if (n == 1) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
	}

	// 회원수정
	@RequestMapping("update.do")
	public void updateDo(@ModelAttribute CrudVO vo, HttpServletResponse response) throws Exception {
		List<CrudVO> list = svc.searchId(vo);
		String checkPwd = vo.getPassword();
		String checkName = vo.getName();
		String beforePwd = list.get(0).getPassword();
		String beforeName = list.get(0).getName();
		if(beforePwd == checkPwd && beforeName == checkName) {//이전값과 바꾼값이 둘다 동일할경우에 false반환
			response.getWriter().print(false);
		}else {
			int n = svc.updateUser(vo);
			if (n == 1) {
				response.getWriter().print(true);
			} 
		}
	}

	// 페이지이동
	@RequestMapping("goUpdate")
	public String goUpdate() throws Exception {
		return "update";
	}

	@RequestMapping("goSearch")
	public String goSearch() throws Exception {
		return "search";
	}

	@RequestMapping("goInsert")
	public String goInsert() throws Exception {
		return "insert";
	}

	@RequestMapping("goLogin")
	public String goLogin() throws Exception {
		return "login";
	}
}
