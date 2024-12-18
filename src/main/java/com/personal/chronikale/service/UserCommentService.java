package com.personal.chronikale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.chronikale.Recorder.UserCommentRecorder;
import com.personal.chronikale.ServiceSAO.CommentSAO;
import com.personal.chronikale.entity.BlogPost;
import com.personal.chronikale.entity.UserComment;
import com.personal.chronikale.exceptions.ResourceNotFound;
import com.personal.chronikale.repository.CommentRepository;
import com.personal.chronikale.repository.UserPostRepository;
@Service
public class UserCommentService implements CommentSAO{
	@Autowired
	private UserPostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public UserCommentRecorder CreateUserComment(UserCommentRecorder commentRecorder, Integer postId) {
		BlogPost userPost= this.postRepository.
				findById(postId).
				orElseThrow(
						()->new ResourceNotFound("Post not found !!")
						);
		
		
		UserComment comment= new UserComment();
		comment.setPost(userPost);
		comment.setContent(commentRecorder.comment());
		
		UserComment savedComment= this.commentRepository.save(comment);
		
		return new UserCommentRecorder(
				savedComment.getId(),
				savedComment.getContent()
				);
		
		
		// TODO Auto-generated method stub

	}

	@Override
	public void deteleComment(Integer commentId) {
		
		this.commentRepository.deleteById(commentId);
		// TODO Auto-generated method stub
		
	}

}
