package com.marina.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.marina.springboot.coufigure.ErrorContext;
import com.marina.springboot.coufigure.PropertiesConfiguration;
import com.marina.springboot.service.LoginService;

@Controller
public class IndexController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	ErrorContext errorContext;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @RequestParam(name = "name")String requestName,
			@RequestParam(name = "pass")String pass) {
		Boolean loginFlag = false;
		loginFlag = loginService.doLogic(requestName, pass);
		
		model.addAttribute("validName", requestName);
		model.addAttribute("validPass", pass);
		
		if (loginFlag == false) {
			model.addAttribute("nameError", errorContext.getNameError());
			model.addAttribute("passError", errorContext.getPassError());
			return "index";
		}		
		return "login";
	}
	
}
