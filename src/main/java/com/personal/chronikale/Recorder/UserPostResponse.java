package com.personal.chronikale.Recorder;

import java.util.Date;
import java.util.Set;

import com.personal.chronikale.entity.UserComment;


public record UserPostResponse(
		String title,
		String content,
		String imageName,
		Date addedDate,
		Set<UserCommentRecorder> comment
		) {





}
