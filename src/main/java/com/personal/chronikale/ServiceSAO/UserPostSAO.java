package com.personal.chronikale.ServiceSAO;

import java.util.List;

import com.personal.chronikale.Recorder.PostCreationRequest;
import com.personal.chronikale.entity.BlogPost;
import com.personal.chronikale.entity.BlogUser;

public interface UserPostSAO {
	// Create Post
	PostCreationRequest createBlogPost(PostCreationRequest blogPostCreationRequest, Integer CategoryId, Integer userId);
	// Update Post
	BlogPost updateBlogPost(PostCreationRequest updateRequest, Integer postId);
	// Delete Post
	void deletePost(Integer postId);
	// get all post
	List<BlogPost> getAllPost();
	
	// getsinglePost
	List<BlogPost> getPostById(Integer postId);
	
	//get All post by category
	List<BlogPost> getPostByCategory(Integer categoryId);
	
	// getAllPostByUser
	
	List<BlogPost> getPostByUser(BlogUser blogUser);
	
	//searchPost
	List<BlogPost> searchPost(String keyword);
	
	
}
