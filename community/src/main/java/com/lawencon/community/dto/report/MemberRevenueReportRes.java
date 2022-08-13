package com.lawencon.community.dto.report;

import java.util.List;

public class MemberRevenueReportRes {
	private List<MemberRevenueReportData> data;
	private Integer count;

	public List<MemberRevenueReportData> getData() {
		return data;
	}

	public void setData(List<MemberRevenueReportData> data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
