package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_types", uniqueConstraints = {
		@UniqueConstraint(name = "thread_types_bk", columnNames = { "thread_types_code" }) })
public class ThreadType extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "thread_types_code")
	private String threadTypeCode;

	@Column(name = "thread_type")
	private String threadType;

	public String getThreadTypeCode() {
		return threadTypeCode;
	}

	public void setThreadTypeCode(String threadTypeCode) {
		this.threadTypeCode = threadTypeCode;
	}

	public String getThreadType() {
		return threadType;
	}

	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}
}