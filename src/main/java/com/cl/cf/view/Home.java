package com.cl.cf.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home {

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/home/insert", method = RequestMethod.GET)
	public String detail() {
		return "home/insert";
	}
	
	@RequestMapping(value="/home/insert", method = RequestMethod.POST)
	public String detail(@RequestParam("name") String name) {
		System.out.println("Name: "+name);
		return "home/done";
	}

}