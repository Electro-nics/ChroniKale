package com.personal.chronikale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.personal.chronikale.entity.BlogCatagory;

@EnableJpaRepositories
@Repository
public interface BlogCatagoryRepository extends JpaRepository<BlogCatagory, Integer>{
	boolean existsByCatagoryTitle(String catagoryTitle);
}
