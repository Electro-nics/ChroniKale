package com.personal.chronikale.Recorder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserResponsePayload(
		
		String name,
		
		String email,
		
		String phoneNumber,
		
		String about	
		) {


}
