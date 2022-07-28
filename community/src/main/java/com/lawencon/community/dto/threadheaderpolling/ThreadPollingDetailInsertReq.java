package com.lawencon.community.dto.threadheaderpolling;

import javax.validation.constraints.NotBlank;

public class ThreadPollingDetailInsertReq {

	@NotBlank(message = "Question can't be empty")
	private String question;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
