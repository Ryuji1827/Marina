package com.marina.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.marina.springboot.coufigure.ErrorContext;
import com.marina.springboot.repository.LoginRepository;
import com.marina.springboot.service.LoginService;

@Controller
public class IndexController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	ErrorContext errorContext;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	HttpSession session;
	
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
		
		int id = loginService.getId(requestName);
		session.setAttribute("id", id);
		
		if (loginFlag == false) {
			model.addAttribute("nameError", errorContext.getNameError());
			model.addAttribute("passError", errorContext.getPassError()	);
			return "index";
		}		
		return "login";
	}
	
	@RequestMapping("/login/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping(value = "/login/main/confirm", method = RequestMethod.POST)
	public String confirm(Model model, @RequestParam(name = "author")String author
			,@RequestParam(name = "title")String title
			,@RequestParam(name = "mainText")String mainText
			,@RequestParam(name = "image")Byte image) {
		
		model.addAttribute("author", author);
		model.addAttribute("title", title);
		model.addAttribute("mainText", mainText);
		model.addAttribute("image", image);
		model.addAttribute("id", session.getAttribute("id"));
		return "confirm";
	}
	
}
