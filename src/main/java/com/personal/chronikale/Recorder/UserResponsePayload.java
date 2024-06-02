package com.personal.chronikale.Recorder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserResponsePayload(
		@NotEmpty
		@Size(min = 4, message="Name must be of minimum 4 character")
		String name,
		@Email(message = "Your email address is not a valid one !!")
		String email,
		@NotEmpty
		@Pattern(regexp = "(^$|[0-9]{10})")
		String phoneNumber,
		@NotEmpty
		String about	
		) {


}
