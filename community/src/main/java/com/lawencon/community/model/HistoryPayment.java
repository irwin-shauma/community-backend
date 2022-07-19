package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "history_payment", uniqueConstraints = {
		@UniqueConstraint(
				name = "history_payment_bk",
				columnNames = "history_payment_code"
			)
		
})
public class HistoryPayment extends BaseEntity {
	
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "history_payment_code")
	private String historyPaymentCode;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private String userId;
	
	@Column(name = "trx_no")
	private String trxNo;

	public String getHistoryPaymentCode() {
		return historyPaymentCode;
	}

	public void setHistoryPaymentCode(String historyPaymentCode) {
		this.historyPaymentCode = historyPaymentCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}

}
