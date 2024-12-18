package com.personal.chronikale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chronikale.Recorder.UserCommentRecorder;
import com.personal.chronikale.ServiceSAO.CommentSAO;
import com.personal.chronikale.responsePayload.ApplicationResponsePayload;

@RestController
@CrossOrigin
@RequestMapping("v1/user-comment")
public class UserCommentController {
	
	@Autowired
	private CommentSAO commentSAO;
	
	@PostMapping("/create-comment")
	public ResponseEntity<UserCommentRecorder> userCommentCreator(
			@RequestBody UserCommentRecorder commentRecorder,
			@RequestParam Integer postId
			
			) {
		UserCommentRecorder userComment=this.commentSAO.CreateUserComment(commentRecorder, postId);
		
				return new ResponseEntity<>(userComment,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete-user-comment")
	public ResponseEntity<ApplicationResponsePayload> deleteUserComment(
			@RequestParam Integer customerId
			
			){
		this.commentSAO.deteleComment(customerId);
		
		return new ResponseEntity<>(
				new ApplicationResponsePayload(
						"Comment Deleted Successfully !!", true
						),
				HttpStatus.OK
				);
	}
}
