package com.personal.chronikale.Recorder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(
		@NotEmpty
		@Size(min = 4, message="Name must be of minimum 4 character")
		String name,
		
		@Email(message = "Your email address is not a valid one !!")
		String email,
		
		@NotEmpty
		@Pattern(regexp = "^(\\d{10}|\\d{3}-\\d{3}-\\d{4})$",
		message="Phone number should be of length 10"
				)
		String phoneNumber,
		
		@NotEmpty
		@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
		message = "Password should be min of length 8 and should contain an uppercase character,"
				+ " a lowercase character, a number and a special character")
		String password,
		
		@NotEmpty
		String about
		) {

}
