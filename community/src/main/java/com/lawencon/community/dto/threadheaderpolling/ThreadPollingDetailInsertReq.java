package com.lawencon.community.dto.threadheaderpolling;

import javax.validation.constraints.NotBlank;

public class ThreadPollingDetailInsertReq {

	@NotBlank(message = "Choice can't be empty")
	private String pollingChoice;

	public String getPollingChoice() {
		return pollingChoice;
	}

	public void setPollingChoice(String pollingChoice) {
		this.pollingChoice = pollingChoice;
	}

}
