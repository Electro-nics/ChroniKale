package com.personal.chronikale.Recorder;

public record UserRegistrationRequest(
		String name,
		String email,
		String phoneNumber,
		String password,
		String about
		) {

}
