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
public class ThreadHeaderPolling extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;
	
	private String titlePolling;
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