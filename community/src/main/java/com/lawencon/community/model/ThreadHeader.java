package com.lawencon.community.model;

import javax.persistence.Entity;
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
public class ThreadHeader extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;

	private String title;
	private ThreadType threadType;
	private String contentThread;
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
	public String getContentThread() {
		return contentThread;
	}
	public void setContentThread(String contentThread) {
		this.contentThread = contentThread;
	}
}
