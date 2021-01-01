package com.marina.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marina.springboot.Posts;
import com.marina.springboot.repository.ConfirmRepository;

@Service
public class EditService {
	
	@Autowired
	ConfirmRepository confirmRepositroy;
	
	public Optional<Posts> doSearch(int id) {
		Optional<Posts> post = Optional.ofNullable(confirmRepositroy.findById(id));
		return post;
	}
}
