package com.eidiko.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.FileData;
import com.eidiko.entity.ImageData;
import com.eidiko.service.StorageService;

@RestController
@RequestMapping("/image")
public class StorageController {

	@Autowired
	private StorageService storageService;

	@GetMapping
	public String welcome() {
		return "Welcome";
	}

	// upload .png file to db
	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		String image = storageService.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK).body(image);
	}

	// download .png file from db
	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
		byte[] imageData = storageService.downloadImage(fileName);

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}

	// upload .png to file system
	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException {
		String image = storageService.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK).body(image);
	}

	// downoad .png from file system
	@GetMapping("/downloadImage/{fileName}")
	public ResponseEntity<?> downloadDocs(@PathVariable String fileName) throws IOException {
		byte[] imageData = storageService.downloadImageFromFileSystem(fileName);

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}


	@GetMapping("/getAllImageData")
	public ResponseEntity<?> getAllImageData() {

		List<ImageData> allFileInfo = storageService.getAllImageData();

		return new ResponseEntity<List<ImageData>>(allFileInfo, HttpStatus.OK);
	}

	@GetMapping("/getAllFileInfo")
	public ResponseEntity<?> getAllFileData() {
		List<FileData> allFileInfo = storageService.getAllFiles();

		return new ResponseEntity<List<FileData>>(allFileInfo, HttpStatus.OK);
	}

}
