package com.lawencon.community.dto;

import java.math.BigDecimal;

public class TotalCountData {
	private Long totalUser;
	private Long totalPremiumUser;
	private Long totalArticle;
	private Long totalThread;
	private Long totalEvent;
	private Long totalCourse;
	private BigDecimal totalRevenue;

	public Long getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(Long totalUser) {
		this.totalUser = totalUser;
	}

	public Long getTotalPremiumUser() {
		return totalPremiumUser;
	}

	public void setTotalPremiumUser(Long totalPremiumUser) {
		this.totalPremiumUser = totalPremiumUser;
	}

	public Long getTotalArticle() {
		return totalArticle;
	}

	public void setTotalArticle(Long totalArticle) {
		this.totalArticle = totalArticle;
	}

	public Long getTotalThread() {
		return totalThread;
	}

	public void setTotalThread(Long totalThread) {
		this.totalThread = totalThread;
	}

	public Long getTotalEvent() {
		return totalEvent;
	}

	public void setTotalEvent(Long totalEvent) {
		this.totalEvent = totalEvent;
	}

	public Long getTotalCourse() {
		return totalCourse;
	}

	public void setTotalCourse(Long totalCourse) {
		this.totalCourse = totalCourse;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

}
