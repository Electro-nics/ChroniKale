package com.personal.chronikale.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.personal.chronikale.ServiceSAO.FileService;
@Configuration
public class BlogFileUploader implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		// File Name
		String name= file.getOriginalFilename();
		
		// GenerateRandomFileName
		String randomID=UUID.randomUUID().toString();
		String fileName=randomID.concat(name.substring(name.lastIndexOf(".")));
		// File Path
		String filePath=path + File.separator + fileName;
		// create folder if not created 
		File imgFolder =new File(path);
		
		if (! imgFolder.exists()) {
			imgFolder.mkdir();
		}
		// file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String fullPath = path+File.separator+fileName;
		InputStream is = new FileInputStream(fullPath); 
		
		
		return is;
	}

}