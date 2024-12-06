package com.personal.chronikale.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.chronikale.Recorder.PostCreationRequest;
import com.personal.chronikale.ServiceSAO.UserPostSAO;
import com.personal.chronikale.entity.BlogCatagory;
import com.personal.chronikale.entity.BlogPost;
import com.personal.chronikale.entity.BlogUser;
import com.personal.chronikale.exceptions.ResourceNotFound;
import com.personal.chronikale.repository.BlogCatagoryRepository;
import com.personal.chronikale.repository.UserPostRepository;
import com.personal.chronikale.repository.UserRepository;
@Service
public class BlogUserPostService implements UserPostSAO{
	@Autowired
	private UserPostRepository postRepository;
	@Autowired
	private BlogCatagoryRepository catagoryRepository;
	@Autowired
	private UserRepository userRepository;

	
	
	
	

	@Override
	public PostCreationRequest createBlogPost(PostCreationRequest postCreationRequest,Integer CategoryId, Integer userId) {
		// TODO Auto-generated method stub
		
		BlogUser blogUser= this.userRepository.findById(userId)
				.orElseThrow(
						()-> new ResourceNotFound(
								"User with id %s is not found"
								.formatted(userId)
								)
						);
		BlogCatagory blogCatagory=this.catagoryRepository.findById(CategoryId)
				.orElseThrow(
						()-> new ResourceNotFound(
								"This catagoty is not found with id %s"
								.formatted(userId)));
		
		BlogPost blogPost= new BlogPost();
		blogPost.setTitle(postCreationRequest.title());
		blogPost.setContent(postCreationRequest.content());
		blogPost.setImageName(postCreationRequest.imageName() !=null
				&& ! postCreationRequest.imageName().isEmpty()
				? postCreationRequest.imageName(): "Default.png");
		blogPost.setAddedDate(new Date());
		blogPost.setUser(blogUser);
		blogPost.setCatagory(blogCatagory);
		BlogPost savedPost= this.postRepository.save(blogPost);

		
		return new PostCreationRequest(
				savedPost.getTitle(),
				savedPost.getContent(),
				savedPost.getImageName(),
				savedPost.getAddedDate(),
				savedPost.getCatagory(),
				savedPost.getUser()
				);
		
	}

	@Override
	public BlogPost updateBlogPost(PostCreationRequest updateRequest, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BlogPost> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogPost> getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogPost> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogPost> getPostByUser(BlogUser blogUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogPost> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
