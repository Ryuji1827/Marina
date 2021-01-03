package com.marina.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marina.springboot.Posts;

@Repository
public interface EditRepository extends JpaRepository<Posts, Integer> {
	@Transactional
	@Modifying
	@Query(value = "UPDATE posts SET title = :edit_title"
			+ ", text = :edit_mainText"
			+ ", image = :image"
			+ ", author_id = :author_id WHERE id = :id", nativeQuery = true)
			public void updateById(@Param("edit_title") String edit_title
					,@Param("edit_mainText") String edit_mainText
					,@Param("image") String image
					,@Param("author_id") int author_id
					,@Param("id") int id);
}
