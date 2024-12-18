package com.personal.chronikale.Recorder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.personal.chronikale.entity.BlogCatagory;
import com.personal.chronikale.entity.BlogUser;
import com.personal.chronikale.entity.UserComment;


public record PostCreationRequest(
		String title,
		String content,
		String imageName,
		Date addedDate,
		BlogCatagory catagory,
		BlogUser user,
		Set<UserCommentRecorder> userComment
		
		
		) {

}
