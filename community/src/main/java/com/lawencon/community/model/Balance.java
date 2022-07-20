package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "balance " , uniqueConstraints = {
		@UniqueConstraint(
				name = "balance_bk",
				columnNames = "balance_code"
			)
})
public class Balance extends BaseEntity{
	
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "balance_code")
	private String balanceCode;
	
	@Column(name = "currentBalance")
	private Float currentBalance;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getBalanceCode() {
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}

	public Float getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Float currentBalance) {
		this.currentBalance = currentBalance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
