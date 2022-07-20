package com.lawencon.community.dto;

public class ErrorRes <T> {
	private T message;

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}

	
}
