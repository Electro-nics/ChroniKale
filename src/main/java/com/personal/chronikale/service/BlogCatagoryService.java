package com.personal.chronikale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.chronikale.DAO.BlogCatagoryDAO;
import com.personal.chronikale.Recorder.BlogCatagoryCreationRequest;
import com.personal.chronikale.Recorder.BlogCatagoryResponsePayload;
import com.personal.chronikale.Recorder.BlogCatagoryUpdateRequest;
import com.personal.chronikale.entity.BlogCatagory;
import com.personal.chronikale.exceptions.DuplicateResourceException;
import com.personal.chronikale.exceptions.RequestValidationExecption;
import com.personal.chronikale.exceptions.ResourceNotFound;
import com.personal.chronikale.repository.BlogCatagoryRepository;

@Service
public class BlogCatagoryService {
@Autowired
private BlogCatagoryDAO blogCatagoryDAO;
@Autowired
private BlogCatagoryRepository catagoryRepository;
	
	public List<BlogCatagoryResponsePayload> getAllBlogCatagory() {
		List<BlogCatagory> allBlogCatagory = blogCatagoryDAO.selectAllBlogCatagory();
		return allBlogCatagory.stream().map(b->
		new BlogCatagoryResponsePayload(
				b.getCatagoryId(),
				b.getCatagoryTitle(),
				b.getCatagoryDescription()
				)
		).collect(Collectors.toList());
	}
	
	public BlogCatagoryResponsePayload getIndivisualBlogCatagory(Integer blog_id) {
		BlogCatagory indivisualCatagory= blogCatagoryDAO.selectBlogCatagoryById(blog_id)
				.orElseThrow(()->
			new ResourceNotFound(
					"No Blog Catagory with id %s found".formatted(blog_id)
					)
		);
		BlogCatagoryResponsePayload catagoryResponsePayload= 
				new BlogCatagoryResponsePayload(
						indivisualCatagory.getCatagoryId(),
						indivisualCatagory.getCatagoryTitle(),
						indivisualCatagory.getCatagoryDescription()
						);
		return catagoryResponsePayload;
	}
	public void createBlogCatagory(BlogCatagoryCreationRequest catagoryCreationRequest) {
		String catagoryTitle=catagoryCreationRequest.catagoryTitle();
		boolean isCatagoryTitlePresent= blogCatagoryDAO.existsByCatagoryTitle(catagoryTitle);
		if(isCatagoryTitlePresent) {
			throw new DuplicateResourceException("This catagory is already present !!");
		}
		
		BlogCatagory createCatagory=new BlogCatagory(
				catagoryTitle,
				catagoryCreationRequest.catagoryDescription()
				);
		catagoryRepository.save(createCatagory);
		
	}
	public void UpdateBlogCatagory(
			BlogCatagoryUpdateRequest catagoryUpdateRequest,
			Integer blog_id
			) {
		BlogCatagory indivisualCatagory=blogCatagoryDAO.selectBlogCatagoryById(blog_id)
				.orElseThrow(
						()-> new ResourceNotFound("Blog Catagory with id %s is not available "
								.formatted(blog_id)));
		boolean change = false;
		if(catagoryUpdateRequest.catagoryTitle()!=null &&
				catagoryUpdateRequest.catagoryTitle()
				.equals(indivisualCatagory.getCatagoryTitle())) {
			if(blogCatagoryDAO.existsByCatagoryTitle(catagoryUpdateRequest.catagoryTitle())) {
				throw new DuplicateResourceException("Catagory title already exists !!");
			}
			indivisualCatagory.setCatagoryTitle(catagoryUpdateRequest.catagoryTitle());
			change =true;
		}
		
		if(catagoryUpdateRequest.catagoryDescription() !=null
				&& !catagoryUpdateRequest.catagoryDescription()
				.equals(indivisualCatagory.getCatagoryDescription())) {
			indivisualCatagory
			.setCatagoryDescription(catagoryUpdateRequest.catagoryDescription());
			change=true;
			
		}
		if(!change) {
			throw new RequestValidationExecption("No changes Reflected !!");
		}
	}
	
	
}
