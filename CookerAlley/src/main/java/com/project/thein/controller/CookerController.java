package com.project.thein.controller;

import javax.servlet.http.HttpSession;

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
import com.project.thein.vo.PagingVO;
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
	   public String search(@RequestParam("gugun1") String loc, @RequestParam("datepick") String date, PagingVO pv,
	         @RequestParam(value = "nowPage", required = false) String nowPage,
	         @RequestParam(value = "cntPerPage", required = false) String cntPerPage, Model model) throws Exception {
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
	      // return new
	      // ModelAndView("search","list",service.search(loc));//검색어(위치/골목길/검색어-필수 아님)로 식당
	      // 리스트 가져와
	      return "search";
	   }
	//식당 상세정보 찾기-------------------------------------------------
	@RequestMapping("detail.do")
	public ModelAndView detail(@RequestParam("cooker") String cooker) {
		//String result = service.toString();
		return new ModelAndView("detail","detail",service.detail(cooker));//식당정보(?이름?사진?)클릭시 식당의 전체정보 가져와
	}
	//회원정보 가져오기-------------------------------------------------
	@RequestMapping("UserInfo.do")
	public ModelAndView info(HttpSession session) {
		//String result = service.toString();
		String id=(String)session.getAttribute("id");
		return new ModelAndView("UserInfo","info",service.info(id));//id로 회원 전체 정보 가져와
	}
	/////////////////////////관리자 페이지
	@RequestMapping("shopMgt.do")
	public ModelAndView Mgt() {
		return new ModelAndView("shopMgt");
	}

}
