package com.lawencon.community.dto.eventpaymenthistory;

import java.time.LocalDateTime;

public class EventPaymentHistoryData {

	private String id;
	private String eventPaymentHistoryCode;
	private String userId;
	private String eventHeaderId;
	private String paymentId;
	private String email;
	private String fullname;
	private String title;
	private Float price;
	private String trxNo;
	private Boolean isAprove;
	private String fileId;
	private Boolean isActive;
	private String eventCreator;
	private Integer version;
	private LocalDateTime createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventPaymentHistoryCode() {
		return eventPaymentHistoryCode;
	}

	public void setEventPaymentHistoryCode(String eventPaymentHistoryCode) {
		this.eventPaymentHistoryCode = eventPaymentHistoryCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getEventHeaderId() {
		return eventHeaderId;
	}

	public void setEventHeaderId(String eventHeaderId) {
		this.eventHeaderId = eventHeaderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}

	public Boolean getIsAprove() {
		return isAprove;
	}

	public void setIsAprove(Boolean isAprove) {
		this.isAprove = isAprove;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getEventCreator() {
		return eventCreator;
	}

	public void setEventCreator(String eventCreator) {
		this.eventCreator = eventCreator;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
