package com.lawencon.community.dto.historypayment;

import java.util.List;

import com.lawencon.model.SearchQuery;

public class FindAllRes {
	private SearchQuery<HistoryPaymentData> data;

	public SearchQuery<HistoryPaymentData> getData() {
		return data;
	}

	public void setData(SearchQuery<HistoryPaymentData> data) {
		this.data = data;
	}
}
