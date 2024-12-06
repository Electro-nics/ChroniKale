package com.personal.chronikale.DAO;

import java.util.List;
import java.util.Optional;

import com.personal.chronikale.entity.BlogCatagory;

public interface BlogCatagoryDAO {
	List<BlogCatagory> selectAllBlogCatagory();
	Optional<BlogCatagory> selectBlogCatagoryById(Integer blog_id);
	Boolean existsBlogCatagoryrById(Integer blog_id);
	void insertBlogCatagory(BlogCatagory blogCatagory);
	void deleteBlogCatagory(Integer blog_id);
	void updateBlogCatagory(BlogCatagory blogCatagory);
	boolean existsByCatagoryTitle(String title);
}
