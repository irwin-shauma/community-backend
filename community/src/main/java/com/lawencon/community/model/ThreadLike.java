package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_like", uniqueConstraints = {
		@UniqueConstraint(
				name = "thread_like_bk",
				columnNames = "thread_like_code"
			)
		
})
public class ThreadLike extends BaseEntity{
	
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "thread_like_code")
	private String threadLikeCode;
	
	@OneToOne
	@JoinColumn(name = "thread_id")
	private ThreadHeader threadHeader;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User userId;

	public String getThreadLikeCode() {
		return threadLikeCode;
	}

	public void setThreadLikeCode(String threadLikeCode) {
		this.threadLikeCode = threadLikeCode;
	}

	public ThreadHeader getThreadHeader() {
		return threadHeader;
	}

	public void setThreadHeader(ThreadHeader threadHeader) {
		this.threadHeader = threadHeader;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}


	

}
