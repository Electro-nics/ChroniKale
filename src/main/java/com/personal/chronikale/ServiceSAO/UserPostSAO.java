package com.personal.chronikale.ServiceSAO;

import java.util.List;

import com.personal.chronikale.Recorder.PostCreationRequest;
import com.personal.chronikale.Recorder.PostResponse;
import com.personal.chronikale.Recorder.UserPostResponse;
import com.personal.chronikale.entity.BlogPost;


public interface UserPostSAO {
	// Create Post
	PostCreationRequest createBlogPost(PostCreationRequest blogPostCreationRequest, Integer CategoryId, Integer userId);
	// Update Post
	BlogPost updateBlogPost(PostCreationRequest updateRequest, Integer postId);
	// Delete Post
	void deletePost(Integer postId);
	// get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize);
	
	// getsinglePost
	UserPostResponse getPostById(Integer postId);
	
	//get All post by category
	PostResponse getPostByCategory(Integer categoryId,int pageNumber,int pageSize);
	
	// getAllPostByUser
	
	PostResponse getPostByUser(Integer userId,int pageNumber,int pageSize);
	
	//searchPost
	List<UserPostResponse> searchPost(String keyword);
	
	
}
