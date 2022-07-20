package com.lawencon.community.dto.threadheaderpolling;


import com.lawencon.model.SearchQuery;

public class FindAllRes {
	private SearchQuery<ThreadHeaderPollingData> data;

	public SearchQuery<ThreadHeaderPollingData> getData() {
		return data;
	}

	public void setData(SearchQuery<ThreadHeaderPollingData> data) {
		this.data = data;
	}




	
}
