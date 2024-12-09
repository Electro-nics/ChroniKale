package com.personal.chronikale.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.chronikale.Recorder.PostCreationRequest;
import com.personal.chronikale.Recorder.UserPostResponse;
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
								.formatted(CategoryId)));
		
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
		BlogPost blogPost= this.postRepository.findById(postId)
				.orElseThrow(
						()-> new ResourceNotFound
						(
								"Post is not found with id %s".formatted(postId)
								)
						);
		this.postRepository.deleteById(postId);

		
	}

	@Override
	public List<UserPostResponse> getAllPost() {
		List<BlogPost> allUserPost = this.postRepository.findAll();
		
		return allUserPost.stream().map(p->
		new UserPostResponse(
				p.getTitle(),
				p.getContent(),
				p.getImageName(),
				p.getAddedDate()
				)
		).collect(Collectors.toList());
	}

	@Override
	public UserPostResponse getPostById(Integer postId) {
		BlogPost userPost = this.postRepository.findById(postId).orElseThrow(
				()-> new ResourceNotFound(
						"This Post is not found with id %s"
						.formatted(postId))
				);

		
		return new UserPostResponse(
				userPost.getTitle(),
				userPost.getContent(),
				userPost.getImageName(),
				userPost.getAddedDate()
				);

//		return userByPost.stream().map(p->
//		new UserPostResponse(
//				p.getTitle(),
//				p.getContent(),
//				p.getImageName(),
//				p.getAddedDate()
//				)
//		).collect(Collectors.toList());
	}

	@Override
	public List<UserPostResponse> getPostByCategory(Integer categoryId) {
		BlogCatagory blogCatagory=this.catagoryRepository.findById(categoryId)
				.orElseThrow(
						()-> new ResourceNotFound(
								"This catagoty is not found with id %s"
								.formatted(categoryId)));
		List<BlogPost> postByCatagory=this.postRepository.findByCatagory(blogCatagory);
		
		return postByCatagory.stream().map(p->
		new UserPostResponse(
				p.getTitle(),
				p.getContent(),
				p.getImageName(),
				p.getAddedDate()
				)
		).collect(Collectors.toList());
		
	}

	@Override
	public List<UserPostResponse> getPostByUser(Integer blogUser) {
		BlogUser indivisualUser= this.userRepository.findById(blogUser)
				.orElseThrow(
						()-> new ResourceNotFound(
								"User with id %s is not found"
								.formatted(blogUser)
								)
						);
		List<BlogPost> postByUser=this.postRepository.findByUser(indivisualUser);
		
		return postByUser.stream().map(p->
		new UserPostResponse(
				p.getTitle(),
				p.getContent(),
				p.getImageName(),
				p.getAddedDate()
				)
		).collect(Collectors.toList());
		

	}

	@Override
	public List<UserPostResponse> searchPost(String keyword) {
		List<BlogPost> searchedPost = this.postRepository.findByTitleContaining(keyword);
		if (searchedPost.isEmpty()) {
			throw  new ResourceNotFound("No Data found"); 
		}
		
		return searchedPost.stream().map(s->
		new UserPostResponse(
				s.getTitle(),
				s.getContent(),
				s.getImageName(),
				s.getAddedDate()
				)
		).collect(Collectors.toList());
	}

}
