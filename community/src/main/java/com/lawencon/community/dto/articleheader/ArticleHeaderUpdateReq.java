package com.lawencon.community.dto.articleheader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ArticleHeaderUpdateReq {

	@NotNull(message = "Id can't be empty")
	private String id;
	private String fileId;
	private String fileName;
	private String fileExtension;

	@NotBlank(message = "Title can't be empty")
	private String title;

	@NotBlank(message = "Contents can't be empty")
	private String contents;

	@NotNull(message = "Active status can't be empty")
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
