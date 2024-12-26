package com.personal.chronikale;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.javafaker.Faker;
import com.personal.chronikale.config.AppConstants;
import com.personal.chronikale.entity.BlogUser;
import com.personal.chronikale.entity.Role;
import com.personal.chronikale.repository.BlogUserRoleRepository;
import com.personal.chronikale.repository.UserRepository;

@SpringBootApplication

public class ChroniKaleApplication implements CommandLineRunner {

	@Autowired
	private BlogUserRoleRepository blogUserRoleRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ChroniKaleApplication.class, args);
	}
	



@Override
public void run(String... args) throws Exception {
	// TODO Auto-generated method stub
	try {
		Role normalRole=new Role();
		normalRole.setId(AppConstants.NORMAL_USER);
		normalRole.setName("NORMAL");
		
		Role adminUser = new Role();
		adminUser.setId(AppConstants.ADMIN_USER);
		adminUser.setName("ADMIN");
		
		List<Role> definedRoles=List.of(adminUser,normalRole);
		
		this.blogUserRoleRepository.saveAllAndFlush(definedRoles);
		
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	
}
	

}
