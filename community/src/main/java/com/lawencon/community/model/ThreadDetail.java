package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "email_bk",
				columnNames = {"email"}
		),
		@UniqueConstraint(
				name = "verification_bk",
				columnNames = {"nama", "univ_id"}
				),
})
public class ThreadDetail extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;

	@ManyToOne
	@JoinColumn(name = "thread_header_id")
	private ThreadHeader threadHeader;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

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

}
