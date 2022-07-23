package com.lawencon.community.dto.premiumtype;

import javax.validation.constraints.NotNull;

public class PremiumTypeInsertReq {
	
	@NotNull(message = "Price can't be empty")
	private Float price;
	
	@NotNull(message = "Duration can't be empty")
	private Integer duration;

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
