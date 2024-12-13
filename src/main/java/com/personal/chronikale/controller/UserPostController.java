package com.personal.chronikale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chronikale.Recorder.PostCreationRequest;
import com.personal.chronikale.Recorder.PostResponse;
import com.personal.chronikale.Recorder.UserPostResponse;
import com.personal.chronikale.ServiceSAO.UserPostSAO;
import com.personal.chronikale.responsePayload.ApplicationResponsePayload;

@RestController
@CrossOrigin
@RequestMapping("v1/userblog/post")
public class UserPostController {
	@Autowired
	private UserPostSAO userPostSAO;
	@PostMapping("/create-post")
	public ResponseEntity<PostCreationRequest> userPostCreation(
			@RequestBody PostCreationRequest creationRequest,
			@RequestParam Integer catagoryId,
			@RequestParam Integer userId
			){
		PostCreationRequest savedDetails=userPostSAO.createBlogPost(
				creationRequest, catagoryId, userId
				);
				return new ResponseEntity<>(savedDetails,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete-post")
	public ResponseEntity<ApplicationResponsePayload> deletePost(
			@RequestParam Integer postId
			){
		userPostSAO.deletePost(postId);
		
				return new ResponseEntity<>
				(
						new ApplicationResponsePayload
						(
								"Post deleted Successfully !!", true
								),
						HttpStatus.OK
						);
		
	}
	@GetMapping("/allpost")
	public ResponseEntity<PostResponse> allUsersPost(
			@RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam (value = "pageSize",defaultValue = "10",required = false) Integer pageSize
			){
		PostResponse allPost= userPostSAO.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<>(allPost,HttpStatus.FOUND);
		
	}
	@GetMapping("/indivisual-post")
	public ResponseEntity<UserPostResponse> indivisualPost(
			@RequestParam Integer postId
			)
	{
		UserPostResponse singlePost = userPostSAO.getPostById(postId);
		return new ResponseEntity<>(singlePost,HttpStatus.FOUND);
	}
	
	@GetMapping("/postbycatagory")
	public ResponseEntity<PostResponse> postByCatagory(@RequestParam Integer catagoryId,
			@RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam (value = "pageSize",defaultValue = "10",required = false) Integer pageSize
			){
		PostResponse catagoryWisePost = userPostSAO.getPostByCategory(catagoryId,pageNumber,pageSize);
		return new ResponseEntity<>(catagoryWisePost,HttpStatus.FOUND);
	}
	@GetMapping("/postbyuser")
	public ResponseEntity<PostResponse> postByIndivisualUser(@RequestParam Integer userId,
			@RequestParam (value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam (value = "pageSize",defaultValue = "10",required = false) Integer pageSize){
		PostResponse postByUser= userPostSAO.getPostByUser(userId,pageNumber,pageSize);
		return new ResponseEntity<>(postByUser,HttpStatus.FOUND);
	}
	@GetMapping("/searchedpost")
	public ResponseEntity<List<UserPostResponse>> searchPost(
			@RequestParam String keyword
			){
		List<UserPostResponse> searchedPost= userPostSAO.searchPost(keyword);
		return new ResponseEntity<>(searchedPost,HttpStatus.FOUND);
	}
}
