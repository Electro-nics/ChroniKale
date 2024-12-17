package com.personal.chronikale.Recorder;

import java.util.Date;

public record UserPostUpdateRequest(
		String title,
		String content,
		String imageName,
		Date addedDate
		) {

}
