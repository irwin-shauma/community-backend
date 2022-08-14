package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_polling_like", uniqueConstraints = {
		@UniqueConstraint(name = "thread_polling_like_bk", columnNames = "thread_polling_like_code")

})
public class ThreadPollingLike extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "thread_polling_like_code")
	private String threadPollingLikeCode;

	@OneToOne
	@JoinColumn(name = "thread_polling_header_id")
	private ThreadHeaderPolling threadHeaderPolling;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User userId;

	public String getThreadPollingLikeCode() {
		return threadPollingLikeCode;
	}

	public void setThreadPollingLikeCode(String threadPollingLikeCode) {
		this.threadPollingLikeCode = threadPollingLikeCode;
	}

	public ThreadHeaderPolling getThreadHeaderPolling() {
		return threadHeaderPolling;
	}

	public void setThreadHeaderPolling(ThreadHeaderPolling threadHeaderPolling) {
		this.threadHeaderPolling = threadHeaderPolling;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

}
