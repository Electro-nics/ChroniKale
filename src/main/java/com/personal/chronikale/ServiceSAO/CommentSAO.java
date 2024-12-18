package com.personal.chronikale.ServiceSAO;

import com.personal.chronikale.Recorder.UserCommentRecorder;

public interface CommentSAO {
	// Create Comment
	UserCommentRecorder CreateUserComment(UserCommentRecorder commentRecorder,Integer postId); 

	// deleteComment
	void deteleComment(Integer commentId);
}
