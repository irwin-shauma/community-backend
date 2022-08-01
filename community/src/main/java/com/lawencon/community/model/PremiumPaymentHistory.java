package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "premium_payment_history", uniqueConstraints = {
		@UniqueConstraint(name = "premium_payment_history_bk", columnNames = "premium_payment_history_code") })
public class PremiumPaymentHistory extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "premium_payment_history_code")
	private String premiumPaymentHistoryCode;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "premium_type_id")
	private PremiumType premiumType;

	@Column(name = "trx_no")
	private String trxNo;

	public String getPremiumPaymentHistoryCode() {
		return premiumPaymentHistoryCode;
	}

	public void setPremiumPaymentHistoryCode(String premiumPaymentHistoryCode) {
		this.premiumPaymentHistoryCode = premiumPaymentHistoryCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PremiumType getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(PremiumType premiumType) {
		this.premiumType = premiumType;
	}

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}
}
