package com.personal.chronikale.Recorder;

public record UserUpdateRequest(
		String name,
		String email,
		String phoneNumber,
		String password,
		String about
		) {

}
