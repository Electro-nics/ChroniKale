package com.personal.chronikale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.personal.chronikale.entity.BlogCatagory;
import com.personal.chronikale.entity.BlogPost;
import com.personal.chronikale.entity.BlogUser;
@EnableJpaRepositories
public interface UserPostRepository extends JpaRepository<BlogPost, Integer>{
	List<BlogPost> findByUser(BlogUser user);
	List<BlogPost> findByCatagory(BlogCatagory catagory);

}
