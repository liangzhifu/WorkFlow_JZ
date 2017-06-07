package com.success.sys.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomePageController {

	@RequestMapping("/homePage.do")
	public String homePage(){
		return "firstPage/firstPage";
	}
	
}
