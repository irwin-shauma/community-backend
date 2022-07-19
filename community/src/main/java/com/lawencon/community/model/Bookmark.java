package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "bookmark", uniqueConstraints = {
		@UniqueConstraint(
				name = "bookmark_code_bk",
				columnNames = "bookmark_code" 
			)
})
public class Bookmark extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "bookmark_code")
	private String bookmarkCode;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "thread_id")
	private String threadId;

	public String getBookmarkCode() {
		return bookmarkCode;
	}

	public void setBookmarkCode(String bookmarkCode) {
		this.bookmarkCode = bookmarkCode;
	}

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

}
