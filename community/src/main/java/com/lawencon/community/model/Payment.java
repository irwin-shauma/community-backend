package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "payment_bk",
				columnNames = "payment_code"
			)
})
public class Payment extends BaseEntity{
	
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "payment_code")
	private String paymentCode;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private String fileId;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private String userId;

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
