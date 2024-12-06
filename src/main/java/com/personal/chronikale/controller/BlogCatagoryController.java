package com.personal.chronikale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.chronikale.Recorder.BlogCatagoryCreationRequest;
import com.personal.chronikale.Recorder.BlogCatagoryResponsePayload;
import com.personal.chronikale.Recorder.BlogCatagoryUpdateRequest;
import com.personal.chronikale.responsePayload.ApplicationResponsePayload;
import com.personal.chronikale.service.BlogCatagoryService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("v1/Blog-Catagory")

public class BlogCatagoryController {
	
@Autowired
private BlogCatagoryService blogCatagoryService;
@GetMapping("/all-blog-Catagory")
public ResponseEntity<List<BlogCatagoryResponsePayload>>AllavaibleCatagories(){
	List<BlogCatagoryResponsePayload> catagoryResponse = blogCatagoryService.getAllBlogCatagory();
	return new ResponseEntity<>(catagoryResponse,HttpStatus.OK);
}

@GetMapping("/individual-cataory")
public ResponseEntity<BlogCatagoryResponsePayload> indivisualBlogCatagoryById(
		@RequestParam Integer CatagoryId
		)
{
	BlogCatagoryResponsePayload indivisualCatagory= blogCatagoryService.getIndivisualBlogCatagory(CatagoryId);
	return new ResponseEntity<>(indivisualCatagory,HttpStatus.OK);
	
}

@PostMapping("/create-catagory")
public ResponseEntity<ApplicationResponsePayload> createNewBlogCatagory(
		@Valid
		@RequestBody BlogCatagoryCreationRequest catagoryRegistration
		){
	  blogCatagoryService.createBlogCatagory(catagoryRegistration);
	
	
	  return new ResponseEntity<>(
				new ApplicationResponsePayload(
						"Blog Catagory created successfully !!", true
						), HttpStatus.OK);
	
}
@PutMapping("/catagory-update")
public ResponseEntity<ApplicationResponsePayload> updateRequest(
		@Valid
		@RequestBody BlogCatagoryUpdateRequest blogCatagoryUpdateRequest,
		@RequestParam Integer catagoryid
		)
{
	blogCatagoryService.UpdateBlogCatagory(blogCatagoryUpdateRequest, catagoryid);

	return new ResponseEntity<>(
			new ApplicationResponsePayload(
					"Blog Catagory Updated Successfully !!", true
					), HttpStatus.OK);
	
}


}
