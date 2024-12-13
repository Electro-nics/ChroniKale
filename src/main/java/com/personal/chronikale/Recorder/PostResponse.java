package com.personal.chronikale.Recorder;

import java.util.List;

public record PostResponse(
		List<UserPostResponse> content,
		int pageNumber,
		int pageSize,
		long totalElements,
		int totalPages,
		boolean lastpage
		) {

}
