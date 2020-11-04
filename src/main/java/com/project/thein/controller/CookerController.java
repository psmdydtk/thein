package com.project.thein.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.thein.service.CookerService;
import com.project.thein.vo.ShopVO;
import com.project.thein.vo.UserVO;


@Controller
public class CookerController {
	private CookerService service;
	public CookerController(CookerService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("main.do")
	public ModelAndView home() {
		return new ModelAndView("main");
	}
	//로그인---------------------------------------------------------------
	@RequestMapping("login.do")
	@ResponseBody
	public String loginDo(@ModelAttribute UserVO vo,HttpSession session) throws Exception{
		Uservo vo=service.login(vo);
		System.out.println();
		if(list !=null) {
			System.out.println(vo.getUser_id()+vo.getUser_type()+vo.getUser_pwd());
			
			session.setAttribute("id",vo.getUser_id());
			session.setAttribute("uType", vo.getUser_type());
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
		return "redirect:main.do";
	}
		
	//검색입력받아 식당 리스트 뽑기-------------------------------------------------
	   @RequestMapping("search.do")
	   public String search(@RequestParam("gugun1") String loc,@RequestParam("datepick") String date) throws Exception {
	      System.out.println("gugun1 = " +loc + "datepick = " + date);
	      List<ShopVO> vo=service.search(loc);
	      //String result = service.toString();
	      //return new ModelAndView("search","list",service.search(loc,date));//검색어(위치/골목길/검색어-필수 아님)로 식당 리스트 가져와
	      vo.toString();
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
	

}
