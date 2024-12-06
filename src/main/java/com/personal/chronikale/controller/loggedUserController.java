package com.personal.chronikale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class loggedUserController {
	// user-Dashboard
	@GetMapping("/dashbaord")
	public String userDeshboard() {
		
		return "user/dashboard";
		
	}
	
	// user Profile
	@GetMapping("/profile")
	public String userProfile() {
		
		return "user/profile";
		
	}
	

}
