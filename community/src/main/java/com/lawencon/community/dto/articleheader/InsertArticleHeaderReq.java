package com.lawencon.community.dto.articleheader;

import javax.validation.constraints.NotNull;

public class InsertArticleHeaderReq {
	
	@NotNull
	private String fileId;
	
	@NotNull
	private String title;
	
	@NotNull
	private String contents;
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
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
