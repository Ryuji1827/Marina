package com.marina.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marina.springboot.Posts;
import com.sun.el.stream.Optional;

@Repository
public interface ConfirmRepository extends JpaRepository<Posts, Integer> {
	
	public Posts findById(int id);
}
