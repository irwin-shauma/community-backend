package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "payment", uniqueConstraints = { @UniqueConstraint(name = "payment_bk", columnNames = "payment_code") })
public class Payment extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "payment_code")
	private String paymentCode;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_approve")
	private Boolean isApprove;

	@OneToOne
	@JoinColumn(name = "premium_type_id")
	private PremiumType premiumType;

	@OneToOne
	@JoinColumn(name = "event_header_id")
	private EventHeader eventHeader;

	@Column(name = "trx_no")
	private String trxNo;

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}

	public PremiumType getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(PremiumType premiumType) {
		this.premiumType = premiumType;
	}

	public EventHeader getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(EventHeader eventHeader) {
		this.eventHeader = eventHeader;
	}

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}

}
