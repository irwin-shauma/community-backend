package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "premium_payment_history")
public class PremiumPaymentHistory extends BaseEntity {
	
	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "premium_payment_history")
	private PremiumType premiumType;
	
	@Column
	private String trxNo;

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
