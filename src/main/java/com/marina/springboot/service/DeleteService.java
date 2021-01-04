package com.marina.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marina.springboot.repository.DeleteRepository;

@Service
public class DeleteService {
	
	@Autowired
	DeleteRepository deleteRepositroy;
	
	public void delete(int id) {
		deleteRepositroy.deleteById(id);
	}

}
