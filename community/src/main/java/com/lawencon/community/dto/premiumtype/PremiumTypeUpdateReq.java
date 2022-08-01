package com.lawencon.community.dto.premiumtype;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PremiumTypeUpdateReq {

	@NotBlank(message = "Id can't be empty")
	private String id;

	@NotNull(message = "Price can't be empty")
	private Float price;

	@NotNull(message = "Duration can't be empty")
	private Integer duration;

	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
