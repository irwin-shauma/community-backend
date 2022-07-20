package com.lawencon.community.dto.balance;

import java.util.List;

import com.lawencon.model.SearchQuery;

public class FindAllRes {
	
	private SearchQuery<BalanceData> data;

	public SearchQuery<BalanceData> getData() {
		return data;
	}

	public void setData(SearchQuery<BalanceData> data) {
		this.data = data;
	}
}
