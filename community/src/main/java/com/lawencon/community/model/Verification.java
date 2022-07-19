package com.lawencon.community.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "email_bk",
				columnNames = {"email"}
		),
		@UniqueConstraint(
				name = "verification_bk",
				columnNames = {"nama", "univ_id"}
				),
})
public class Verification extends BaseEntity{
	private static final long serialVersionUID = -5196455701225322056L;
	
	private String verification;
	private LocalDateTime expired;
	
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	public LocalDateTime getExpired() {
		return expired;
	}
	public void setExpired(LocalDateTime expired) {
		this.expired = expired;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}