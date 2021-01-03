package com.marina.springboot.controller;

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
	ConfirmService confirmService;
	
	@Autowired
	EditService editService;
	
	@Autowired
	ConfirmRepository confirmRepository;
		
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "/main/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		Optional<Posts> post = Optional.ofNullable(confirmRepository.findById(id));
		Posts cpost = post.get();
	
		model.addAttribute("title", cpost.getTitle());
		model.addAttribute("mainText", cpost.getText());

		System.out.println("title:" + cpost.getTitle() + ", mainText:" + cpost.getText());
		String image = cpost.getImage();
		if(image != null) {
			model.addAttribute("image", image);
		}
		
		session.setAttribute("id", id);
		session.setAttribute("edit_post", cpost);
		session.setAttribute("image", image);
		return "edit";	
	}
	
//	@RequestMapping(value = "/main/edit/confirm", method = RequestMethod.POST)
//	public String confirm(Model model,@RequestParam(name = "edit_title")String title
//			,@RequestParam(name = "edit_mainText")String mainText
//			,@RequestParam(name = "edit_image")MultipartFile image) {
//		model.addAttribute("title", title);
//		model.addAttribute("mainText", mainText);
//		model.addAttribute("image", image);
//		model.addAttribute("target", "edit");
//		
//		session.setAttribute("edit_title", title);
//		session.setAttribute("edit_mainText", mainText);
//		session.setAttribute("edit_image", image);
//
//		return "confirm";
//	}
	
	@RequestMapping(value = "/main/edit/done", method = RequestMethod.GET)
	public String update(Model model
			,@RequestParam(name = "edit_title")String edit_title
			,@RequestParam(name = "edit_mainText")String edit_mainText
			,@RequestParam(name = "image") MultipartFile image) {
				int edit_id = (int)session.getAttribute("edit_id");
				int edit_author_id = (int) session.getAttribute("login_id");

				String image_name = confirmService.savefile(image);
				if(edit_id != 0) {
					model.addAttribute("did", "編集しました");
					editService.update(edit_title, edit_mainText, image_name, edit_author_id, edit_id);
				}
					return "done";
				
	}
}
