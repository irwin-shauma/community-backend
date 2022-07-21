package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.EventDetailDao;
import com.lawencon.community.dao.EventHeaderDao;
import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.eventdetail.EventDetailData;
import com.lawencon.community.dto.eventdetail.EventDetailFindByIdRes;
import com.lawencon.community.dto.eventdetail.EventDetailInsertReq;
import com.lawencon.community.dto.eventdetail.EventDetailUpdateReq;
import com.lawencon.community.model.EventDetail;
import com.lawencon.community.model.EventHeader;
import com.lawencon.community.model.File;
import com.lawencon.model.SearchQuery;

public class EventDetailService extends BaseCoreService<EventDetail>{
	
	private EventDetailDao eventDetailDao;
	private EventHeaderDao eventHeaderDao;
	private FileDao fileDao;
	
	public InsertRes insert(EventDetailInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			EventDetail eventDetail = new EventDetail();
			
			EventHeader eventHeader = new EventHeader();
			eventHeader.setId(data.getEventHeaderId());
			eventDetail.setEventHeader(eventHeader);
			
			File  file = new File();
			file.setId(data.getFileId());
			eventDetail.setFile(file);
			
			eventDetail.setPrice(data.getPrice());
			eventDetail.setDates(data.getDates());
			eventDetail.setStarts(data.getStarts());
			eventDetail.setEnds(data.getEnds());
			eventDetail.setProvider(data.getProvider());
			eventDetail.setLocations(data.getLocations());
			eventDetail.setIsActive(true);

			begin();
			EventDetail eventDetailInsert = save(eventDetail);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(eventDetailInsert.getId());

			result.setData(insertDataRes);
//			result.setMessage(MessageResponse.SAVED.name());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	
	public UpdateRes update(EventDetailUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			EventDetail eventDetailDb = eventDetailDao.getById(data.getId());
			eventDetailDb.setId(data.getId());
			
			EventHeader eventHeaderDb = eventHeaderDao.getById(data.getEventHeaderId());
			eventDetailDb.setEventHeader(eventHeaderDb);
			
			File fileDb = fileDao.getById(data.getFileId());
			eventDetailDb.setFile(fileDb);
			
			eventDetailDb.setPrice(data.getPrice());
			eventDetailDb.setDates(data.getDates());
			eventDetailDb.setStarts(data.getStarts());
			eventDetailDb.setEnds(data.getEnds());
			eventDetailDb.setProvider(data.getProvider());
			eventDetailDb.setLocations(data.getLocations());
		
			eventDetailDb.setIsActive(data.getIsActive());
			eventDetailDb.setVersion(data.getVersion());

			begin();
			EventDetail eventDetailUpdate = eventDetailDao.save(eventDetailDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(eventDetailUpdate.getVersion());

			result.setData(updateDataRes);
//			result.setMessage(MessageResponse.SAVED.name());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	
	public EventDetailFindByIdRes getById(String id) throws Exception {
		EventDetail eventDetailDb = eventDetailDao.getById(id);

		EventDetailData data = new EventDetailData();
		data.setId(eventDetailDb.getId());
		data.setEventDetailCode(eventDetailDb.getEventDetailCode());
		data.setEventHeaderId(eventDetailDb.getEventHeader().getId());
		data.setFileId(eventDetailDb.getFile().getId());
		data.setDates(eventDetailDb.getDates());
		data.setStarts(eventDetailDb.getStarts());
		data.setEnds(eventDetailDb.getEnds());
		data.setProvider(eventDetailDb.getProvider());
		data.setLocations(eventDetailDb.getLocations());
		data.setIsActive(eventDetailDb.getIsActive());
		data.setVersion(eventDetailDb.getVersion());

		EventDetailFindByIdRes result = new EventDetailFindByIdRes();
		result.setData(data);

		return result;
	}
	
	
	public SearchQuery<EventDetailData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<EventDetail> dataDb = eventDetailDao.findAll(query, startPage, maxPage);

		List<EventDetailData> eventDetailDataList = new ArrayList<EventDetailData>();

		dataDb.getData().forEach(eventDetail -> {
			EventDetailData data = new EventDetailData();
			data.setId(eventDetail.getId());
			data.setEventDetailCode(eventDetail.getEventDetailCode());
			data.setEventHeaderId(eventDetail.getEventHeader().getId());
			data.setFileId(eventDetail.getFile().getId());
			data.setDates(eventDetail.getDates());
			data.setStarts(eventDetail.getStarts());
			data.setEnds(eventDetail.getEnds());
			data.setProvider(eventDetail.getProvider());
			data.setLocations(eventDetail.getLocations());
			data.setIsActive(eventDetail.getIsActive());
			data.setVersion(eventDetail.getVersion());

			eventDetailDataList.add(data);
		});

		SearchQuery<EventDetailData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(eventDetailDataList);

		return result;
	}
	
	
	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

//		result.setMessage(MessageResponse.FAILED.name());

		try {
			begin();
			boolean isDeleted = eventDetailDao.deleteById(id);
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
