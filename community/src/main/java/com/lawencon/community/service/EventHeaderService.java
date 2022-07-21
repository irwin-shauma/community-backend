package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.EventHeaderDao;
import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.eventheader.EventHeaderData;
import com.lawencon.community.dto.eventheader.EventHeaderFindByIdRes;
import com.lawencon.community.dto.eventheader.EventHeaderInsertReq;
import com.lawencon.community.dto.eventheader.EventHeaderUpdateReq;
import com.lawencon.community.model.EventHeader;
import com.lawencon.community.model.EventType;
import com.lawencon.model.SearchQuery;

@Service
public class EventHeaderService extends BaseCoreService<EventHeader>{
	
	@Autowired
	private EventHeaderDao eventHeaderDao;
	@Autowired
	private EventTypeDao eventTypeDao;
	
	public InsertRes insert(EventHeaderInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			EventHeader eventHeader = new EventHeader();
			eventHeader.setEventHeaderCode(data.getEventHeaderCode());
			eventHeader.setTitle(data.getTitle());
			
			EventType eventType = new EventType();
			eventType.setId(data.getEventTypeId());
			
			eventHeader.setEventTypeId(eventType);
			
			eventHeader.setIsActive(true);

			begin();
			EventHeader eventHeaderInsert = save(eventHeader);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(eventHeaderInsert.getId());

			result.setData(insertDataRes);
//			result.setMessage(MessageResponse.SAVED.name());	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	
	public UpdateRes update(EventHeaderUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			EventHeader eventHeaderDb = eventHeaderDao.getById(data.getId());
			eventHeaderDb.setEventHeaderCode(data.getEventHeaderCode());
			eventHeaderDb.setTitle(data.getTitle());
			
			EventType eventDb = eventTypeDao.getById(data.getId());
			
			eventHeaderDb.setEventTypeId(eventDb);

			eventHeaderDb.setIsActive(data.getIsActive());
			eventHeaderDb.setVersion(data.getVersion());

			begin();
			EventHeader eventHeaderUpdate = eventHeaderDao.save(eventHeaderDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(eventHeaderUpdate.getVersion());

			result.setData(updateDataRes);
//			result.setMessage(MessageResponse.SAVED.name());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	
	public EventHeaderFindByIdRes getById(String id) throws Exception {
		EventHeader eventHeaderDb = eventHeaderDao.getById(id);

		EventHeaderData data = new EventHeaderData();
		data.setId(eventHeaderDb.getId());
		data.setEventHeaderCode(eventHeaderDb.getEventHeaderCode());
		data.setTitle(eventHeaderDb.getTitle());
		data.setEventTypeId(eventHeaderDb.getEventType().getId());
		data.setVersion(eventHeaderDb.getVersion());

		EventHeaderFindByIdRes result = new EventHeaderFindByIdRes();
		result.setData(data);

		return result;
	}
	
	
	public SearchQuery<EventHeaderData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<EventHeader> dataDb = eventHeaderDao.findAll(query, startPage, maxPage);

		List<EventHeaderData> eventHeaderDataList = new ArrayList<EventHeaderData>();

		dataDb.getData().forEach(eventHeader -> {
			EventHeaderData data = new EventHeaderData();
			data.setId(eventHeader.getId());
			data.setEventHeaderCode(eventHeader.getEventHeaderCode());
			data.setTitle(eventHeader.getTitle());
			data.setEventTypeId(eventHeader.getEventType().getId());
			data.setVersion(eventHeader.getVersion());

			eventHeaderDataList.add(data);
		});

		SearchQuery<EventHeaderData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(eventHeaderDataList);

		return result;
	}
	
	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

//		result.setMessage(MessageResponse.FAILED.name());

		try {
			begin();
			boolean isDeleted = eventHeaderDao.deleteById(id);
			commit();

			if (isDeleted) {
//				result.setMessage(MessageResponse.DELETED.name());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	

}
