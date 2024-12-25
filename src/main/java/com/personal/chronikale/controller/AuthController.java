package com.personal.chronikale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chronikale.Recorder.JWTAuthRequest;
import com.personal.chronikale.Recorder.JWTAuthResponse;
import com.personal.chronikale.Recorder.RegistrationResponsePayload;
import com.personal.chronikale.exceptions.UserLoginExecption;
import com.personal.chronikale.security.JWTTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	
	// user login API
	public ResponseEntity<JWTAuthResponse> createToken(
			@RequestBody JWTAuthRequest payload
			){
		
		this.authenticate(payload.username(),payload.password());
		
		UserDetails userDetails= this.userDetailsService.loadUserByUsername(payload.username());
		
		String token=this.jwtTokenHelper.generateToken(userDetails);
		JWTAuthResponse authResponse= new JWTAuthResponse(token);
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
		
	}
	private void authenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
				new UsernamePasswordAuthenticationToken(username, password);
		try {
		this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		}
		catch ( BadCredentialsException e) {
			throw new UserLoginExecption("Username or Password is incorrect !!");
			// TODO: handle exception
		}
		
	}
}
