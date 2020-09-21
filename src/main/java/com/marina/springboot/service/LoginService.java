package com.marina.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.marina.springboot.Users;
import com.marina.springboot.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository loginRepository;
	
	public boolean doLogic(String name, String pass) {
		List<Users> users = loginRepository.findAll();
		for(Users user : users) {
			if(!(name.equals(user.getName())) || !(pass.equals(user.getPassword()))) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}
}
