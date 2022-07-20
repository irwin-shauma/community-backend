package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.model.EventType;
import com.lawencon.model.SearchQuery;

@Service
public class EventTypeService extends BaseCoreService {

	@Autowired
	private EventTypeDao eventTypeDao;
	
	public EventType insert(EventType data) throws Exception {
		try {
			begin();
			eventTypeDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return data;
	}
	
	public EventType update(EventType data) throws Exception {
		try {
			EventType mhsDb = eventTypeDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			eventTypeDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public EventType getById(String id) throws Exception {
		return eventTypeDao.getById(id);
	}

	public SearchQuery<EventType> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return eventTypeDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = eventTypeDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
}
