package com.marina.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marina.springboot.repository.EditRepository;

@Service
public class EditService {
	
	@Autowired
	EditRepository editRepository;
	
	@Autowired
	ConfirmService confirmService;
	
	public void update(String edit_title, String edit_mainText, String image, int edit_authorId, int edit_id) {
		editRepository.updateById(edit_title, edit_mainText, image, edit_authorId, edit_id);
	}
	
}
