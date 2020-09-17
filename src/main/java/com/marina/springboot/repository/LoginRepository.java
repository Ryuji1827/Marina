package com.marina.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marina.springboot.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {
	
}
