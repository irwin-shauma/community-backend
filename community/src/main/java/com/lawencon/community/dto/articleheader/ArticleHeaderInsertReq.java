package com.lawencon.community.dto.articleheader;

import javax.validation.constraints.NotBlank;

public class ArticleHeaderInsertReq {

	private String fileName;
	private String fileExtension;

	@NotBlank(message = "Title can't be empty")
	private String title;

	@NotBlank(message = "Contents can't be empty")
	private String contents;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
