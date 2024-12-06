package com.personal.chronikale.Recorder;

import java.util.Date;

import com.personal.chronikale.entity.BlogCatagory;
import com.personal.chronikale.entity.BlogUser;

import jakarta.validation.constraints.NotNull;

public record PostCreationRequest(

		String title,
		String content,
		String imageName,
		Date addedDate,
		BlogCatagory catagory,
		BlogUser user
		) {

}
