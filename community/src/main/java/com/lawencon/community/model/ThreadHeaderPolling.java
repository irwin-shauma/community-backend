package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_header_polling", uniqueConstraints = {
		@UniqueConstraint(name = "thread_header_polling_code_bk", columnNames = "thread_header_polling_code") })
public class ThreadHeaderPolling extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "thread_header_polling_code")
	private String threadHeaderPollingCode;

	@Column(name = "title_polling")
	private String titlePolling;

	@Column(name = "content_polling")
	private String contentPolling;

	public String getThreadHeaderPollingCode() {
		return threadHeaderPollingCode;
	}

	public void setThreadHeaderPollingCode(String threadHeaderPollingCode) {
		this.threadHeaderPollingCode = threadHeaderPollingCode;
	}

	public String getTitlePolling() {
		return titlePolling;
	}

	public void setTitlePolling(String titlePolling) {
		this.titlePolling = titlePolling;
	}

	public String getContentPolling() {
		return contentPolling;
	}

	public void setContentPolling(String contentPolling) {
		this.contentPolling = contentPolling;
	}

}