package com.llw.hospital.common.util;

import org.springframework.core.io.ByteArrayResource;

public class UploadFileResource extends ByteArrayResource {
	
	private String fileName;

	public UploadFileResource(byte[] byteArray,String fileName) {
		super(byteArray);
		this.fileName = fileName;
	}

	public UploadFileResource(byte[] byteArray,String fileName,String description) {
		super(byteArray, description);
		this.fileName = fileName;
	}

	@Override
	public String getFilename() {
		return this.fileName;
	}
	
}
