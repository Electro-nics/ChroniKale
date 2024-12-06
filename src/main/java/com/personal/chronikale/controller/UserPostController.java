package com.personal.chronikale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chronikale.Recorder.PostCreationRequest;
import com.personal.chronikale.ServiceSAO.UserPostSAO;

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
		PostCreationRequest savedDetails=userPostSAO.createBlogPost(creationRequest, catagoryId, userId);
				return new ResponseEntity<>(savedDetails,HttpStatus.CREATED);
	}
}
