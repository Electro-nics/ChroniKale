package com.personal.chronikale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chronikale.Recorder.RegistrationResponsePayload;
import com.personal.chronikale.Recorder.UserRegistrationRequest;
import com.personal.chronikale.Recorder.UserResponsePayload;
import com.personal.chronikale.Recorder.UserUpdateRequest;
import com.personal.chronikale.responsePayload.ApplicationResponsePayload;
import com.personal.chronikale.service.BlogUserService;

import jakarta.validation.Valid;

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

@PostMapping("/user-registration")
public ResponseEntity<String> registerUser(@Valid
		@RequestBody UserRegistrationRequest userRegistrationRequest,
		BindingResult bindingResult
		){
	
	if(bindingResult.hasErrors()) {
		return new ResponseEntity<>("Register",HttpStatus.NOT_ACCEPTABLE);
	}
	String bloguserRegistration= blogUserService
			.userRegistration(userRegistrationRequest);
	
	
	return new ResponseEntity<>(bloguserRegistration, HttpStatus.OK);
	
}
@PutMapping("/user-update")
public ResponseEntity<ApplicationResponsePayload> updateRequest(
		@Valid
		@RequestBody UserUpdateRequest userUpdateRequest,
		@RequestParam Integer userid
		)
{
	 blogUserService
			.userUpdate(userUpdateRequest, userid);

	return new ResponseEntity<>(
			new ApplicationResponsePayload(
					"User Updated Successfully !!", true
					), HttpStatus.OK);
	
}

@DeleteMapping("/delete-user")
public ResponseEntity<ApplicationResponsePayload> deleteBlogUser(
		@RequestParam Integer userId
		)
{
	blogUserService.deleteUser(userId);
	return new ResponseEntity<>(new ApplicationResponsePayload("User Deleted Successfully !!", true), HttpStatus.OK);
}





}
