package com.lawencon.community.dto.threadheaderpolling;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class ThreadHeaderPollingInsertReq {

	@NotBlank(message = "Title can't be empty")
	private String titlePolling;

	@NotBlank(message = "Content can't be empty")
	private String contentPolling;

	@NotBlank(message = "Question can't be empty")
	private List<ThreadPollingDetailInsertReq> threadPollingDetail;

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

	public List<ThreadPollingDetailInsertReq> getThreadPollingDetail() {
		return threadPollingDetail;
	}

	public void setThreadPollingDetail(List<ThreadPollingDetailInsertReq> threadPollingDetail) {
		this.threadPollingDetail = threadPollingDetail;
	}

}
