package com.personal.chronikale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.personal.chronikale.entity.UserComment;
@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<UserComment, Integer>{
	
	
}
