package com.eidiko.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.FileData;
import com.eidiko.entity.ImageData;
import com.eidiko.exceptions.ResourceNotFoundException;
import com.eidiko.repository.FileDataRepository;
import com.eidiko.repository.ImageDataRepository;
import com.eidiko.util.FileNameUtils;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private ImageDataRepository imageDataRepo;

	@Autowired
	private FileDataRepository fileDataRepo;

	private final String FOLDER_PATH = "C:/Users/Sreenivas Bandaru/Desktop/Samarkanta Rout/EXTRA/";
	
	// upload .png to db
	@Override
	public String uploadImage(MultipartFile file) throws IOException {
		String extension = FileNameUtils.getFileExtension(file.getOriginalFilename());
		if (FileNameUtils.isSupportedExtension(extension)) {
			// ImageData imgData =
			imageDataRepo.save(ImageData.builder().name(file.getOriginalFilename()).type(file.getContentType())
					.imageData(file.getBytes()).build());

			return "File uploaded succesfully ! " + file.getOriginalFilename();
		} else

			return "FIle uploading unsuccesfull ! Supported file format : '.png' ";
	}

	// download .png from db
	@Override
	public byte[] downloadImage(String fileName) {
		Optional<ImageData> dbImageData = imageDataRepo.findByName(fileName);
		byte[] image = dbImageData.get().getImageData();
		return image;
	}

	// upload .png to file system
	@Override
	public String uploadImageToFileSystem(MultipartFile file) throws IOException {

		String filePath = FOLDER_PATH + file.getOriginalFilename();
		String extension = FileNameUtils.getFileExtension(file.getOriginalFilename());
		if (FileNameUtils.isSupportedExtension(extension)) {
			// FileData fileData =
			fileDataRepo.save(FileData.builder().name(file.getOriginalFilename()).type(file.getContentType())
					.filePath(filePath).build());

			file.transferTo(new File(filePath));

			return "File uploaded succesfully ! " + filePath;
		} else

			return "FIle uploading unsuccesfull ! Supported file format : '.png ' ";
	}

	// download .png from file system
	@Override
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {

//		boolean fileInfo = fileDataRepo.findByName(fileName).isPresent();
//		if (fileInfo) {

		FileData fileData = fileDataRepo.findByName(fileName)
				.orElseThrow(() -> new ResourceNotFoundException("File not found with this name " + fileName));

		String filePath = fileData.getFilePath();
		byte[] image = Files.readAllBytes(new File(filePath).toPath());
		return image;

//		} else
//			return null;
	}

	@Override
	public List<FileData> getAllFiles() {

		return fileDataRepo.findAll();
	}

	@Override
	public List<ImageData> getAllImageData() {
		return imageDataRepo.findAll();
	}

}
