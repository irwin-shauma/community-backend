package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_details", uniqueConstraints = {
		@UniqueConstraint(name = "thread_detail_code_bk", columnNames = "thread_detail_code") })
public class ThreadDetail extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "thread_detail_code")
	private String threadDetailCode;

	@OneToOne
	@JoinColumn(name = "thread_header_id")
	private ThreadHeader threadHeader;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "comment_thread")
	private String commentThread;

	public String getThreadDetailCode() {
		return threadDetailCode;
	}

	public void setThreadDetailCode(String threadDetailCode) {
		this.threadDetailCode = threadDetailCode;
	}

	public ThreadHeader getThreadHeader() {
		return threadHeader;
	}

	public void setThreadHeader(ThreadHeader threadHeader) {
		this.threadHeader = threadHeader;
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
