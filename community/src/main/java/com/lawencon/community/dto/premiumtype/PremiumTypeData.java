package com.lawencon.community.dto.premiumtype;

public class PremiumTypeData {
	private String id;
	private String premiumTypeCode;
	private Float price;
	private Integer duration;
	private Boolean isActive;
	private Integer version;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}
