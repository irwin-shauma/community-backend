package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.EventHeaderDao;
import com.lawencon.community.model.EventHeader;
import com.lawencon.community.model.Role;
import com.lawencon.model.SearchQuery;

@Service
public class EventHeaderService extends BaseCoreService{
	@Autowired
	private EventHeaderDao eventHeaderDao;
	
	public EventHeader insert(EventHeader data) throws Exception{
		try {
			begin();
			eventHeaderDao.save(data);
			commit();
		}catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
		return data;
	}
	
	public EventHeader update(EventHeader data) throws Exception {
		try {
			EventHeader mhsDb = eventHeaderDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			eventHeaderDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public EventHeader getById(String id) throws Exception {
		return eventHeaderDao.getById(id);
	}

	public SearchQuery<EventHeader> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return eventHeaderDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = eventHeaderDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}


}
