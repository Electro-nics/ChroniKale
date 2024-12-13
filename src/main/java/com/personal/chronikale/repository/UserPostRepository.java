package com.personal.chronikale.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.personal.chronikale.entity.BlogCatagory;
import com.personal.chronikale.entity.BlogPost;
import com.personal.chronikale.entity.BlogUser;
@EnableJpaRepositories
public interface UserPostRepository extends JpaRepository<BlogPost, Integer>{
	Page<BlogPost> findByUser(BlogUser user, Pageable pageable);
	Page<BlogPost> findByCatagory(BlogCatagory catagory, Pageable pageable);
	List<BlogPost> findByTitleContaining(String title);
//	Page<BlogPost> findByCatagoryId(Integer catagoryId, Pageable pageable);

}
