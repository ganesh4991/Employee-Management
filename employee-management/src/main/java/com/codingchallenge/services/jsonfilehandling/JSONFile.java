package com.codingchallenge.services.jsonfilehandling;

import org.springframework.web.multipart.MultipartFile;

public class JSONFile {
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
