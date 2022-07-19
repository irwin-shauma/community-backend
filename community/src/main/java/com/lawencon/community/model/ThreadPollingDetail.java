package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_polling_detail")
public class ThreadPollingDetail extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "thread_header_polling_id")
	private ThreadHeaderPolling threadHeaderPolling;
	
	@Column(name = "question")
	private String question;
	public ThreadHeaderPolling getThreadHeaderPolling() {
		return threadHeaderPolling;
	}
	public void setThreadHeaderPolling(ThreadHeaderPolling threadHeaderPolling) {
		this.threadHeaderPolling = threadHeaderPolling;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
