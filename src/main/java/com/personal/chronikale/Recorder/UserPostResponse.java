package com.personal.chronikale.Recorder;

import java.util.Date;


public record UserPostResponse(
		String title,
		String content,
		String imageName,
		Date addedDate
		) {

}
