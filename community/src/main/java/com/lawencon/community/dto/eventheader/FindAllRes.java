package com.lawencon.community.dto.eventheader;

import java.util.List;

import com.lawencon.model.SearchQuery;

public class FindAllRes {
	private SearchQuery<EventHeaderData> data;

	public SearchQuery<EventHeaderData> getData() {
		return data;
	}

	public void setData(SearchQuery<EventHeaderData> data) {
		this.data = data;
	}
	
}
