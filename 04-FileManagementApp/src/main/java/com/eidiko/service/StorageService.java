package com.eidiko.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.FileData;
import com.eidiko.entity.ImageData;

public interface StorageService {

	public String uploadImage(MultipartFile file) throws IOException;

	public byte[] downloadImage(String fileName) throws IOException;

	public String uploadImageToFileSystem(MultipartFile file) throws IOException;

	public byte[] downloadImageFromFileSystem(String fileName) throws IOException;

	public List<FileData> getAllFiles();

	public List<ImageData> getAllImageData();
}
