package com.marina.springboot.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.marina.springboot.service.ConfirmService;

@Controller
public class PostController {
	
	@Autowired
	ConfirmService confirmService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/main/post")
	public String post() {
		return "post";
	}
	
	@RequestMapping(value = "/main/post/done", method = RequestMethod.POST)
	public String confirm(Model model,@RequestParam(name = "title")String title
			,@RequestParam(name = "mainText")String mainText
			,@RequestParam(name = "image") MultipartFile image) {
		
		model.addAttribute("title", title);
		model.addAttribute("mainText", mainText);
		model.addAttribute("image", image);
		model.addAttribute("target", "post");
		model.addAttribute("did", "投稿");
		
		int author_id = (int) session.getAttribute("login_id");
		try {
			confirmService.doInsert(title, mainText, image, author_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		session.setAttribute("title", title);
		session.setAttribute("mainText", mainText);
		session.setAttribute("image", image);
		
		return "done";
	}
	
	@RequestMapping(value = "/main/post/confirm/done", method = RequestMethod.POST)
	public String insert(Model model) {
		
		int author_id = (int) session.getAttribute("login_id");
		String post_title = (String) session.getAttribute("title");
		String post_mainText = (String) session.getAttribute("mainText");
		MultipartFile image = (MultipartFile) session.getAttribute("image");
		model.addAttribute("did", "投稿");
		try {
			confirmService.doInsert(post_title, post_mainText, image, author_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "done";
	}
}
