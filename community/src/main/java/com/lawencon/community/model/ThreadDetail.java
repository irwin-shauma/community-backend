package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_details")
public class ThreadDetail extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "thread_header_id")
	private ThreadHeader threadHeader;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "comment_thread")
	private String commentThread;

	public ThreadHeader getThreadHeader() {
		return threadHeader;
	}

	public void setThreadHeader(ThreadHeader threadHeader) {
		this.threadHeader = threadHeader;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCommentThread() {
		return commentThread;
	}

	public void setCommentThread(String commentThread) {
		this.commentThread = commentThread;
	}
	
	

}
