package com.personal.chronikale.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.chronikale.DAO.UserDAO;
import com.personal.chronikale.Recorder.RegistrationResponsePayload;
import com.personal.chronikale.Recorder.UserRegistrationRequest;
import com.personal.chronikale.Recorder.UserResponsePayload;
import com.personal.chronikale.entity.BlogUser;
import com.personal.chronikale.exceptions.ResourceNotFound;
@Service
public class BlogUserService {
	@Autowired
	private UserDAO userDAO;
	public List<UserResponsePayload> getAllUserDetails(){
		List<BlogUser> userDetails= userDAO.SelectAllUser();
		return userDetails.stream().map(s -> new UserResponsePayload(s.getName(),
				s.getEmail(), s.getPhoneNumber(), s.getAbout())).collect(Collectors.toList());
	}

	public UserResponsePayload getUserDeatilsById(Integer user_id){
		
		BlogUser userById= userDAO.SelectUserDetailsById(user_id).
				orElseThrow(()->new ResourceNotFound("User with id %s is not found".formatted(user_id)));
		UserResponsePayload userResponse= new UserResponsePayload(
				userById.getName(),
				userById.getEmail(),
				userById.getPhoneNumber(),
				userById.getAbout()
				);
		
		return userResponse;	
		
	}
	// Complete regiater User API
	public RegistrationResponsePayload userRegistration(UserRegistrationRequest registrationRequest) {
		
		
		
		return null;
		
	}
	
	


}
