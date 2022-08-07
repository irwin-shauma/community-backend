package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "event_payment_history", uniqueConstraints = {
		@UniqueConstraint(name = "event_payment_history_bk", columnNames = "event_payment_code") })
public class EventPaymentHistory extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "event_payment_code")
	private String evetnPaymentCode;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@OneToOne
	@JoinColumn(name = "event_header_id")
	private EventHeader eventHeader;

	@Column(name = "trx_no")
	private String trxNo;

	public String getEvetnPaymentCode() {
		return evetnPaymentCode;
	}

	public void setEvetnPaymentCode(String evetnPaymentCode) {
		this.evetnPaymentCode = evetnPaymentCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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
