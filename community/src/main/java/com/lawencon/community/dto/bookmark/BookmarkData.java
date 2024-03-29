package com.lawencon.community.dto.bookmark;

public class BookmarkData {

	private String id;
	private String bookmarkCode;
	private String userId;
	private String threadId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookmarkCode() {
		return bookmarkCode;
	}

	public void setBookmarkCode(String bookmarkCode) {
		this.bookmarkCode = bookmarkCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

}
