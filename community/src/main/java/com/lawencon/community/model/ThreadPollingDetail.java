package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.OneToOne;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_polling_detail", uniqueConstraints = {
		@UniqueConstraint(name = "thread_polling_detail_bk", columnNames = "thread_polling_detail_code") })
public class ThreadPollingDetail extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "thread_polling_detail_code")
	private String threadPollingDetailCode;

	@OneToOne
	@JoinColumn(name = "thread_header_polling_id")
	private ThreadHeaderPolling threadHeaderPolling;

	@Column(name = "polling_choice")
	private String pollingChoice;

	public String getThreadPollingDetailCode() {
		return threadPollingDetailCode;
	}

	public void setThreadPollingDetailCode(String threadPollingDetailCode) {
		this.threadPollingDetailCode = threadPollingDetailCode;
	}

	public ThreadHeaderPolling getThreadHeaderPolling() {
		return threadHeaderPolling;
	}

	public void setThreadHeaderPolling(ThreadHeaderPolling threadHeaderPolling) {
		this.threadHeaderPolling = threadHeaderPolling;
	}

	public String getPollingChoice() {
		return pollingChoice;
	}

	public void setPollingChoice(String pollingChoice) {
		this.pollingChoice = pollingChoice;
	}

}
