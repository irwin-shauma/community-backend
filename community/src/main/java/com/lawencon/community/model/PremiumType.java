package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "premium_type", uniqueConstraints = {
		@UniqueConstraint(name = "premium_type_bk", 
				columnNames = "premium_type_code") })
public class PremiumType extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "premium_type_code")
	private String premiumTypeCode;

	@Column(name = "price")
	private Float price;

	@Column(name = "duration")
	private Integer duration;

	public String getPremiumTypeCode() {
		return premiumTypeCode;
	}

	public void setPremiumTypeCode(String premiumTypeCode) {
		this.premiumTypeCode = premiumTypeCode;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

}
