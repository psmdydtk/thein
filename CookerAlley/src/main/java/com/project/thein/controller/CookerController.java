package com.project.thein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.thein.service.CookerService;

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
	
	@RequestMapping("search.do")
	public ModelAndView search(@RequestParam("loc") String loc,
			@RequestParam("datepick") String date) {
		//String result = service.toString();
		return new ModelAndView("search","list",service.search(loc,date));
	}
	
	@RequestMapping("detail.do")
	public ModelAndView detail(@RequestParam("cooker") String cooker) {
		//String result = service.toString();
		return new ModelAndView("detail","detail",service.detail(cooker));
	}
	

}
