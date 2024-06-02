package com.personal.chronikale;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.javafaker.Faker;
import com.personal.chronikale.entity.BlogUser;
import com.personal.chronikale.repository.UserRepository;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class ChroniKaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChroniKaleApplication.class, args);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Bean
	CommandLineRunner runner(UserRepository userRepository) {
		Faker fake= new Faker();
		String name= fake.name().name();
		String email= fake.internet().safeEmailAddress();
		String phoneNumber= fake.phoneNumber().phoneNumber();
		String password= "MyUser@11";
		String aboutMe= "My name is "+name+", I am a Developer";
		
		return args->{
			BlogUser blogUser=new BlogUser(name, email, phoneNumber,
					password, aboutMe);
			List<BlogUser> userInfo= List.of(blogUser);
			userRepository.saveAll(userInfo);
		};
				}
	

}
