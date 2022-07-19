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
public class ThreadPollingDetail extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;

	private ThreadHeaderPolling threadHeaderPolling;
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
