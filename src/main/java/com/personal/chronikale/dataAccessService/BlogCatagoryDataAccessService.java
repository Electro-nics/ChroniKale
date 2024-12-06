package com.personal.chronikale.dataAccessService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.personal.chronikale.DAO.BlogCatagoryDAO;
import com.personal.chronikale.entity.BlogCatagory;
import com.personal.chronikale.repository.BlogCatagoryRepository;
@Repository
public class BlogCatagoryDataAccessService implements BlogCatagoryDAO {
	@Autowired
	private BlogCatagoryRepository blogCatagoryRepository;

	@Override
	public List<BlogCatagory> selectAllBlogCatagory() {
		return blogCatagoryRepository.findAll();
		
	}

	@Override
	public Optional<BlogCatagory> selectBlogCatagoryById(Integer blog_id) {
		
		return blogCatagoryRepository.findById(blog_id);
		
	}

	@Override
	public Boolean existsBlogCatagoryrById(Integer blog_id) {
		
		return blogCatagoryRepository.existsById(blog_id) ;
	}

	@Override
	public void insertBlogCatagory(BlogCatagory blogCatagory) {
		blogCatagoryRepository.save(blogCatagory);
		
	}

	@Override
	public  void deleteBlogCatagory(Integer blog_id) {
		// TODO Auto-generated method stub
		blogCatagoryRepository.deleteById(blog_id);
		
	}

	@Override
	public void updateBlogCatagory(BlogCatagory blogCatagory) {
		blogCatagoryRepository.save(blogCatagory);
		// TODO Auto-generated method stub
	}

	@Override
	public boolean existsByCatagoryTitle(String title) {
		
		return blogCatagoryRepository.existsByCatagoryTitle(title);
		
	}

	

}
