package com.marina.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marina.springboot.Posts;

public interface DeleteRepository extends JpaRepository<Posts, Integer> {
	void deleteById(int id);
}
