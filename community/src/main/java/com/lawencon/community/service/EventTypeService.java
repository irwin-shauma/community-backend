package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.eventtype.EventTypeData;
import com.lawencon.community.dto.eventtype.EventTypeFindByIdRes;
import com.lawencon.community.dto.eventtype.EventTypeInsertReq;
import com.lawencon.community.dto.eventtype.EventTypeUpdateReq;
import com.lawencon.community.model.EventType;
import com.lawencon.model.SearchQuery;

@Service
public class EventTypeService extends BaseCoreService<EventType> {

	@Autowired
	private EventTypeDao eventTypeDao;
	
	public InsertRes insert(EventTypeInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			EventType eventType = new EventType();
			eventType.setType(data.getType());
			eventType.setIsActive(true);
			
			begin();
			EventType inserted= save(eventType);
			commit();
			
			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(inserted.getId());

			result.setData(insertDataRes);
			result.setMessage("Success");

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	public UpdateRes update(EventTypeUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			EventType eventType = eventTypeDao.getById(data.getId());
			eventType.setType(data.getType());
			eventType.setIsActive(data.getIsActive());
			begin();
			EventType update = save(eventType);
			commit();
			
			UpdateDataRes dataRes = new UpdateDataRes();
			dataRes.setVersion(update.getVersion());
			
			result.setData(dataRes);
			result.setMessage("Success");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public EventTypeFindByIdRes getById(String id) throws Exception {
		EventType eventType = eventTypeDao.getById(id);
		
		EventTypeData event = new EventTypeData();
		event.setId(eventType.getId());
		event.setType(eventType.getType());
		event.setIsActive(eventType.getIsActive());
		event.setVersion(eventType.getVersion());
		
		EventTypeFindByIdRes result = new EventTypeFindByIdRes();
		result.setData(event);
		
		return result;
	}

	public SearchQuery<EventTypeData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<EventType> dataDb = eventTypeDao.findAll(query, startPage, maxPage);
		
		List<EventTypeData> data = new ArrayList<>();
		dataDb.getData().forEach(eventType -> {
			EventTypeData event = new EventTypeData();
			event.setId(eventType.getId());
			event.setType(eventType.getType());
			event.setIsActive(eventType.getIsActive());
			event.setVersion(eventType.getVersion());
			
			data.add(event);
		});
		
		SearchQuery<EventTypeData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(data);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();
		result.setMessage("Failed");
		try {
			begin();
			boolean isDeleted = eventTypeDao.deleteById(id);
			commit();
			if (isDeleted) {
				result.setMessage("Sucess");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
}
