package com.lawencon.community.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_headers", uniqueConstraints = {
		@UniqueConstraint(name = "thread_header_bk", columnNames = "thread_header_code") })
public class ThreadHeader extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "thread_header_code")
	private UUID threadHeaderCode;

	@Column(name = "title")
	private String title;

	@OneToOne
	@JoinColumn(name = "thread_type_id")
	private ThreadType threadType;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "content_thread")
	private String contentThread;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	public UUID getThreadHeaderCode() {
		return threadHeaderCode;
	}

	public void setThreadHeaderCode(UUID threadHeaderCode) {
		this.threadHeaderCode = threadHeaderCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ThreadType getThreadType() {
		return threadType;
	}

	public void setThreadType(ThreadType threadType) {
		this.threadType = threadType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContentThread() {
		return contentThread;
	}

	public void setContentThread(String contentThread) {
		this.contentThread = contentThread;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
