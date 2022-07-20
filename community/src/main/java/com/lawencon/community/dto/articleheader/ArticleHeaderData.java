package com.lawencon.community.dto.articleheader;

public class ArticleHeaderData {

	private String id;
	private String articleHeaderCode;
	private String fileId;
	private String title;
	private String contents;
	private Boolean isActive;
	private Integer version;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArticleHeaderCode() {
		return articleHeaderCode;
	}
	public void setArticleHeaderCode(String articleHeaderCode) {
		this.articleHeaderCode = articleHeaderCode;
	}
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	

	
	
}
