package com.marina.springboot.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.marina.springboot.Posts;
import com.marina.springboot.repository.ConfirmRepository;
import com.marina.springboot.service.ConfirmService;
import com.marina.springboot.service.EditService;

@Controller
public class EditController {
	
	@Autowired
	EditService editService;
	
	@Autowired
	ConfirmRepository confirmRepository;
	
	@Autowired
	ConfirmService confirmService;
	
	@Autowired
	HttpSession session;
	
	
	@RequestMapping(value = "/main/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		Optional<Posts> post = Optional.ofNullable(confirmRepository.findById(id));
		Posts cpost = post.get();
		
		session.setAttribute("edit_id", id);
		session.setAttribute("edit_post", cpost);
		
		model.addAttribute("title", cpost.getTitle());
		model.addAttribute("mainText", cpost.getText());
		System.out.println("mainText:" + cpost.getText());
		String image = cpost.getImage();
		if(image != null) {
			model.addAttribute("image", image);
		}
		return "edit";	
	}
	
	@RequestMapping(value = "/main/edit/confirm", method = RequestMethod.POST)
	public String confirm(Model model,@RequestParam(name = "edit_title")String title
			,@RequestParam(name = "edit_mainText")String mainText
			,@RequestParam(name = "edit_image")MultipartFile image) {
		model.addAttribute("title", title);
		model.addAttribute("mainText", mainText);
		model.addAttribute("image", image);
		model.addAttribute("target", "edit");
		
		session.setAttribute("edit_title", title);
		session.setAttribute("edit_mainText", mainText);
		session.setAttribute("edit_image", image);

		return "confirm";
	}
	
	@RequestMapping(value = "/main/edit/confirm/done", method = RequestMethod.GET)
	public String update() {
		int edit_id = 0;
		edit_id = (int)session.getAttribute("edit_id");
		
		if(edit_id != 0) {
			String edit_title = (String)session.getAttribute("edit_title");
			String edit_mainText = (String)session.getAttribute("edit_mainText");
			MultipartFile edit_image = (MultipartFile) session.getAttribute("edit_image");
			int author_id = (int)session.getAttribute("id");
			try {
				confirmService.doInsert(edit_title, edit_mainText, edit_image, author_id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "done";
	}
}
