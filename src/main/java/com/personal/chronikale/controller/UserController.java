package com.personal.chronikale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chronikale.Recorder.UserResponsePayload;
import com.personal.chronikale.service.BlogUserService;

@RestController
@CrossOrigin
@RequestMapping("/v1/blog-user")
public class UserController {
@Autowired
private BlogUserService blogUserService;
@GetMapping("/all-users")
public ResponseEntity<List<UserResponsePayload>> fatchAllUserDetails(){
	List<UserResponsePayload> userDetails= blogUserService.getAllUserDetails();
	return new ResponseEntity<>(userDetails,HttpStatus.OK);
}

@GetMapping("/individual-user")
public ResponseEntity<UserResponsePayload> userDetailsById(
		@RequestParam Integer userId
		)
{
	UserResponsePayload userById= blogUserService.getUserDeatilsById(userId);
	return new ResponseEntity<>(userById,HttpStatus.OK);
	
}



}
