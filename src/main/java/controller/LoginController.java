package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.LoginService;

public class LoginController {
	
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void login() {
		String name = loginService.doLogic();
		System.out.println(name);
	}
}
