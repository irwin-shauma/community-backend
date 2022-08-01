package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.EventHeaderDao;
import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.dao.FileDao;
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
import com.lawencon.community.model.File;
import com.lawencon.model.SearchQuery;

@Service
public class EventHeaderService extends BaseCoreService<EventHeader> {

	@Autowired
	private EventHeaderDao eventHeaderDao;

	@Autowired
	private EventTypeDao eventTypeDao;

	@Autowired
	private FileDao fileDao;

	public InsertRes insert(EventHeaderInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			begin();
			EventHeader eventHeader = new EventHeader();
			eventHeader.setTitle(data.getTitle());

			EventType eventType = eventTypeDao.getById(data.getEventTypeId());
			eventHeader.setEventTypeId(eventType);

			if (data.getFileName() != null) {
				File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				fileDao.save(file);

				eventHeader.setFile(file);
			}

			eventHeader.setIsActive(true);

			EventHeader eventHeaderInsert = save(eventHeader);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(eventHeaderInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
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
			begin();
			EventHeader eventHeaderDb = eventHeaderDao.getById(data.getId());
			eventHeaderDb.setTitle(data.getTitle());

			EventType eventDb = eventTypeDao.getByIdWithoutDetach(data.getId());
			eventHeaderDb.setEventTypeId(eventDb);
			eventHeaderDb.setIsActive(data.getIsActive());

			if (data.getFileId() != null && data.getFileName() != null) {
				File file = fileDao.getById(data.getFileId());
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				fileDao.save(file);

				eventHeaderDb.setFile(file);
				
			} else if(data.getFileId() == null && data.getFileName() != null) {
				File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				File fileRes = fileDao.save(file);
				eventHeaderDb.setFile(fileRes);
			} else if (data.getFileId() == null) {
				File formerFile = fileDao.getByIdWithoutDetach(eventHeaderDb.getFile().getId());
				eventHeaderDb.setFile(null);
				fileDao.delete(formerFile);
				
			}

			EventHeader eventHeaderUpdate = eventHeaderDao.save(eventHeaderDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(eventHeaderUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());

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
		if (eventHeaderDb.getFile() != null) {
			data.setFileId(eventHeaderDb.getFile().getId());
		}
		data.setTitle(eventHeaderDb.getTitle());
		data.setEventTypeId(eventHeaderDb.getEventType().getId());
		data.setIsActive(eventHeaderDb.getIsActive());
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
			if (eventHeader.getFile() != null) {
				data.setFileId(eventHeader.getFile().getId());
			}
			data.setTitle(eventHeader.getTitle());
			data.setEventTypeId(eventHeader.getEventType().getId());
			data.setIsActive(eventHeader.getIsActive());
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
		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			
			EventHeader eventHeader = eventHeaderDao.getById(id);
			if(eventHeader.getFile() != null) {
				fileDao.deleteById(eventHeader.getFile().getId());
			}
			
			boolean isDeleted = eventHeaderDao.deleteById(id);
			commit();

			if (isDeleted) {
				result.setMessage(MessageResponse.DELETED.getMessageResponse());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

}
