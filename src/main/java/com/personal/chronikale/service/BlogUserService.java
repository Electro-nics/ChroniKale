package com.personal.chronikale.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.personal.chronikale.DAO.UserDAO;
import com.personal.chronikale.Recorder.RegistrationResponsePayload;
import com.personal.chronikale.Recorder.UserRegistrationRequest;
import com.personal.chronikale.Recorder.UserResponsePayload;
import com.personal.chronikale.Recorder.UserUpdateRequest;
import com.personal.chronikale.config.AppConstants;
import com.personal.chronikale.entity.BlogUser;
import com.personal.chronikale.entity.Role;
import com.personal.chronikale.exceptions.DuplicateResourceException;
import com.personal.chronikale.exceptions.RequestValidationExecption;
import com.personal.chronikale.exceptions.ResourceNotFound;
import com.personal.chronikale.repository.BlogUserRoleRepository;
import com.personal.chronikale.repository.UserRepository;
@Service
public class BlogUserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private BlogUserRoleRepository blogUserRoleRepository;
	@Autowired
	private UserRepository userRepository;
	
	
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

	public String userRegistrationAdmin(UserRegistrationRequest registrationRequest) {
		String email= registrationRequest.email();
		String phoneNumber=registrationRequest.phoneNumber();
		Boolean isDuplicateEmailPresent=userDAO.existsuserWithDUplicateEmail(email);
		Boolean isDuplicatePhonePresent=userDAO.existsuserWithDuplicatePhoneNumber(phoneNumber);
		if (isDuplicateEmailPresent) {
			throw new DuplicateResourceException("Email is already used !!");
		}
		if(isDuplicatePhonePresent) {
			throw new DuplicateResourceException("Phone Number Already in User");
		}
		
		Role adminUserRole= this.blogUserRoleRepository.findById(AppConstants.ADMIN_USER).get();
		
		BlogUser blogUser= new BlogUser();
				blogUser.setName(registrationRequest.name()); 
				blogUser.setEmail(registrationRequest.email()); 
				blogUser.setPhoneNumber(registrationRequest.phoneNumber()); 
				blogUser.setPassword(
						this.passwordEncoder.encode(
								registrationRequest
								.password()
								)
						); 
				blogUser.setAbout(registrationRequest.about()); 
				blogUser.setRoles(Set.of(adminUserRole));
		
		
		Boolean isSaved=userDAO.insertUser(blogUser, registrationRequest.email());
			if (isSaved) {
				return "Admin User registration Successfull";
			}
			
			return "Something went wrong !! ";
		
	}
	
	
	public UserResponsePayload normalUserRegistration(UserRegistrationRequest registrationRequest) {
		String email= registrationRequest.email();
		String phoneNumber=registrationRequest.phoneNumber();
		Boolean isDuplicateEmailPresent=userDAO.existsuserWithDUplicateEmail(email);
		Boolean isDuplicatePhonePresent=userDAO.existsuserWithDuplicatePhoneNumber(phoneNumber);
		if (isDuplicateEmailPresent) {
			throw new DuplicateResourceException("Email is already used !!");
		}
		if(isDuplicatePhonePresent) {
			throw new DuplicateResourceException("Phone Number Already in User");
		}
		
		Role userRole= this.blogUserRoleRepository.findById(AppConstants.NORMAL_USER).get();

		
		BlogUser blogUser= new BlogUser();
		blogUser.setName(registrationRequest.name());
		blogUser.setRoles(Set.of(userRole));
		blogUser.setAbout(registrationRequest.about());
		blogUser.setEmail(registrationRequest.email());
		blogUser.setPassword(
				this.passwordEncoder.encode(
						registrationRequest.password()
						)
				);
		blogUser.setPhoneNumber(registrationRequest.phoneNumber());
		
		BlogUser savedRequest= this.userRepository.save(blogUser);
		
		return new UserResponsePayload(
				savedRequest.getName(),
				savedRequest.getEmail(),
				savedRequest.getPhoneNumber(),
				savedRequest.getAbout()
				);
		
	}
	
	public void deleteUser(Integer userId ) {
		if(! userDAO.existsUserById(userId)) {
			throw new ResourceNotFound("User with id %s is not found".formatted(userId));
		}
		
		 userDAO.deleteUser(userId);
		 
		
	}
	
	// Update User
	public void userUpdate(UserUpdateRequest updateRequest, Integer userId) {
		BlogUser userById=userDAO.SelectUserDetailsById(userId).orElseThrow(()->new ResourceNotFound("User with id %s is not found".formatted(userId)));
		boolean change= false;
		// check name validation
		if(updateRequest.name()!=null && ! updateRequest.name().equals(userById.getName())) {
			userById.setName(updateRequest.name());
			change=true;
		}
		
		// check email validation
		if(updateRequest.email() !=null && ! updateRequest.email().equals(userById.getEmail())) {
			
			if(userDAO.existsuserWithDUplicateEmail(updateRequest.email())) {
				throw new DuplicateResourceException("Email id already Exists");
			}
			userById.setEmail(updateRequest.email());
			change= true;
		}
		// Phone NumberValidation
		if(updateRequest.phoneNumber() !=null && ! updateRequest.phoneNumber().equals(userById.getPhoneNumber())) {
					
					if(userDAO.existsuserWithDUplicateEmail(updateRequest.phoneNumber())) {
						throw new DuplicateResourceException("Phone Number already Exists");
					}
					userById.setPhoneNumber(updateRequest.phoneNumber());
					change= true;
				}

// About Section Validation

		if(updateRequest.about() !=null &&
				! updateRequest.about().equals(userById.getAbout())) {
			
			userById.setAbout(updateRequest.about());
			change= true;
		}

// Validate Update Password
		if(updateRequest.password() !=null &&
				! this.passwordEncoder
				.encode(updateRequest.password())
				.equals(userById.getPassword())) {
			userById.setPassword(this.passwordEncoder.encode(updateRequest.password()));
			change = true;
		}
		
		if(change == false) {
			throw new RequestValidationExecption("No changes Reflected ");
		}
		userDAO.updateUser(userById);
		
	}
	
	
	
	


}
