package com.lawencon.community.dto.threadheaderpolling;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ThreadHeaderPollingInsertReq {

	@NotBlank(message = "Title can't be empty")
	private String titlePolling;

	@NotBlank(message = "Content can't be empty")
	private String contentPolling;

	@NotBlank(message = "Question can't be empty")
	private String pollingQuestion;

	@NotBlank(message = "Duration can't be empty")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.000'Z'")
	private LocalDate duration;

	@NotBlank(message = "Question can't be empty")
	private List<ThreadPollingDetailInsertReq> threadPollingDetail;
	
	private String fileName;
	private String fileExtension;
	
	
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

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

	public String getPollingQuestion() {
		return pollingQuestion;
	}

	public void setPollingQuestion(String pollingQuestion) {
		this.pollingQuestion = pollingQuestion;
	}

	public LocalDate getDuration() {
		return duration;
	}

	public void setDuration(LocalDate duration) {
		this.duration = duration;
	}

	public List<ThreadPollingDetailInsertReq> getThreadPollingDetail() {
		return threadPollingDetail;
	}

	public void setThreadPollingDetail(List<ThreadPollingDetailInsertReq> threadPollingDetail) {
		this.threadPollingDetail = threadPollingDetail;
	}

}
