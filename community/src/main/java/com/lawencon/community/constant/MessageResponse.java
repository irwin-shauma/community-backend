package com.lawencon.community.constant;

public enum MessageResponse {
	SAVED("Insert Success!"),
	DELETED("Delete Success!"), 
	UPDATED("Update Success!"),
	FAILED("Failed!");
	
	private String messageResponse;

	private MessageResponse(String messageReponse) {
		this.messageResponse = messageReponse;
	}
	
	public String getMessageResponse() {
		return this.messageResponse;
	}
	
	

}
