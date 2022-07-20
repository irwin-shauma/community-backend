package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_header_polling")
public class ThreadHeaderPolling extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "title_polling")
	private String titlePolling;
	
	@Column(name = "content_polling")
	private String contentPolling;
	
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