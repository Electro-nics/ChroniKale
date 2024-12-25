package com.personal.chronikale.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.personal.chronikale.entity.BlogUser;
import com.personal.chronikale.exceptions.ResourceNotFound;
import com.personal.chronikale.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService{
	

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		loading user from database by userName
	BlogUser user=this.userRepository
			.findByEmail(username)
			.orElseThrow(
					()->new ResourceNotFound("Email not found!!")
					);
		
		return user;
	}

}
