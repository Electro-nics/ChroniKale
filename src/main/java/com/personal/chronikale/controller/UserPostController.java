package com.personal.chronikale.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.chronikale.Recorder.PostCreationRequest;
import com.personal.chronikale.Recorder.PostResponse;
import com.personal.chronikale.Recorder.UserPostResponse;
import com.personal.chronikale.Recorder.UserPostUpdateRequest;
import com.personal.chronikale.ServiceSAO.FileService;
import com.personal.chronikale.ServiceSAO.UserPostSAO;
import com.personal.chronikale.config.AppConstants;
import com.personal.chronikale.responsePayload.ApplicationResponsePayload;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("v1/userblog/post")
public class UserPostController {
	
	
	@Autowired
	private UserPostSAO userPostSAO;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping( "/create-post")
	public ResponseEntity<PostCreationRequest> userPostCreation(
			@RequestPart("creationRequest") String creationRequest,
			@RequestParam Integer catagoryId,
			@RequestParam Integer userId,
			@RequestPart(value = "image", required = false) MultipartFile image
			) throws IOException{
		PostCreationRequest request =mapper.readValue(creationRequest, PostCreationRequest.class);
		PostCreationRequest savedDetails=userPostSAO.createBlogPost(
				request, catagoryId, userId,image
				);
				return new ResponseEntity<>(savedDetails,HttpStatus.CREATED);
	}
	
	
	
	@DeleteMapping("/delete-post")
	public ResponseEntity<ApplicationResponsePayload> deletePost(
			@RequestParam Integer postId
			){
		userPostSAO.deletePost(postId);
		
				return new ResponseEntity<>
				(
						new ApplicationResponsePayload
						(
								"Post deleted Successfully !!", true
								),
						HttpStatus.OK
						);
		
	}
	
	
	
	@GetMapping("/allpost")
	public ResponseEntity<PostResponse> allUsersPost(
			@RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue =AppConstants.SORT_BY,required = false ) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION,required = false) String sortDirection
			){
		PostResponse allPost= userPostSAO.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
		return new ResponseEntity<>(allPost,HttpStatus.FOUND);
		
	}
	@GetMapping("/indivisual-post")
	public ResponseEntity<UserPostResponse> indivisualPost(
			@RequestParam Integer postId
			)
	{
		UserPostResponse singlePost = userPostSAO.getPostById(postId);
		return new ResponseEntity<>(singlePost,HttpStatus.FOUND);
	}
	
	@GetMapping("/postbycatagory")
	public ResponseEntity<PostResponse> postByCatagory(@RequestParam Integer catagoryId,
			@RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize
			){
		PostResponse catagoryWisePost = userPostSAO.getPostByCategory(catagoryId,pageNumber,pageSize);
		return new ResponseEntity<>(catagoryWisePost,HttpStatus.FOUND);
	}
	
	
	
	@GetMapping("/postbyuser")
	public ResponseEntity<PostResponse> postByIndivisualUser(@RequestParam Integer userId,
			@RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize){
		PostResponse postByUser= userPostSAO.getPostByUser(userId,pageNumber,pageSize);
		return new ResponseEntity<>(postByUser,HttpStatus.FOUND);
	}
	
	
	
	@GetMapping("/searchedpost")
	public ResponseEntity<List<UserPostResponse>> searchPost(
			@RequestParam String keyword
			){
		List<UserPostResponse> searchedPost= userPostSAO.searchPost(keyword);
		return new ResponseEntity<>(searchedPost,HttpStatus.FOUND);
	}
	
	
	
	@PostMapping("/user-post/image/upload")
	public ResponseEntity<ApplicationResponsePayload> uploadPostImage(@RequestParam Integer postId,
			@RequestParam("imageFile") MultipartFile imageFile
			) throws IOException{
		
		UserPostResponse userPost= this.userPostSAO.getPostById(postId);
		String fileName=this.fileService.uploadImage(path, imageFile);
		
		Date updateDate =new Date();
		UserPostUpdateRequest imageUpdateRequest= new UserPostUpdateRequest(
				userPost.title(),
				userPost.content(),
				fileName,
				updateDate
				);
		UserPostUpdateRequest updatedResponse=userPostSAO.updateBlogPost(
				imageUpdateRequest, postId
				);
		
		return new ResponseEntity<>(
				new ApplicationResponsePayload("Image Updated Successfully",true),
				HttpStatus.OK
				);
		
	}
	
	@GetMapping("/user-post/image/download/{postImage}")
	public void downloadFile(
			@PathVariable ("postImage") String postImage,
			HttpServletResponse response
			) throws IOException {
		InputStream resourse= this.fileService.getResource(path, postImage);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourse, response.getOutputStream());
		
	}
}
