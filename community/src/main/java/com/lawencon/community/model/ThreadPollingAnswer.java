package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_polling_answer",
		uniqueConstraints = {
		@UniqueConstraint(
				name = "thread_polling_answer_bk", 
				columnNames =  "thread_polling_answer_code" 
				) 
		})
public class ThreadPollingAnswer extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name="thread_polling_answer_code")
	private String threadPollingAnswerCode;

	@OneToOne
	@JoinColumn(name = "thread_polling_id")
	private ThreadHeaderPolling threadHeaderPolling;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getThreadPollingAnswerCode() {
		return threadPollingAnswerCode;
	}

	public void setThreadPollingAnswerCode(String threadPollingAnswerCode) {
		this.threadPollingAnswerCode = threadPollingAnswerCode;
	}

	public ThreadHeaderPolling getThreadHeaderPolling() {
		return threadHeaderPolling;
	}

	public void setThreadHeaderPolling(ThreadHeaderPolling threadHeaderPolling) {
		this.threadHeaderPolling = threadHeaderPolling;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
