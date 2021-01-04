package com.marina.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.marina.springboot.service.DeleteService;

@Controller
public class DeleteController {
	
	@Autowired
	DeleteService deleteService;
	
	@RequestMapping(value = "/main/delete", method = RequestMethod.GET)
	public String showDelete(@RequestParam(defaultValue = "0") int id, Model model) {
		model.addAttribute("id", id);
		return "delete";
	}
	
	@RequestMapping(value = "/main/delete/done", method = RequestMethod.POST)
	public String executeDelete(@RequestParam int id, Model model) {
		deleteService.delete(id);
		model.addAttribute("did", "削除");
		return "done";
	}
}
