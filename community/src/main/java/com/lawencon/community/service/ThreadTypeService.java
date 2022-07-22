package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threadtype.ThreadTypeData;
import com.lawencon.community.dto.threadtype.ThreadTypeFindByIdRes;
import com.lawencon.community.dto.threadtype.ThreadTypeInsertReq;
import com.lawencon.community.dto.threadtype.ThreadTypeUpdateReq;
import com.lawencon.community.model.ThreadType;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadTypeService extends BaseCoreService<ThreadType>{
	@Autowired
	private ThreadTypeDao threadTypeDao;
	
	public InsertRes insert(ThreadTypeInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			ThreadType threadType = new ThreadType();
			threadType.setThreadType(data.getThreadType());
			
			threadType.setIsActive(true);

			begin();
			ThreadType threadTypeInsert = save(threadType);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(threadTypeInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	public UpdateRes update(ThreadTypeUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			ThreadType threadTypeDb = threadTypeDao.getById(data.getId());

			threadTypeDb.setThreadType(data.getThreadType());

			threadTypeDb.setIsActive(data.getIsActive());
			threadTypeDb.setVersion(data.getVersion());

			begin();
			ThreadType threadTypeUpdate = threadTypeDao.save(threadTypeDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(threadTypeUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	public ThreadTypeFindByIdRes getById(String id) throws Exception {
		ThreadType threadTypeDb = threadTypeDao.getById(id);

		ThreadTypeData data = new ThreadTypeData();
		data.setId(threadTypeDb.getId());
		data.setThreadType(threadTypeDb.getThreadType());
	
		data.setIsActive(threadTypeDb.getIsActive());
		data.setVersion(threadTypeDb.getVersion());

		ThreadTypeFindByIdRes result = new ThreadTypeFindByIdRes();
		result.setData(data);

		return result;
	}
	
	public SearchQuery<ThreadTypeData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadType> dataDb = threadTypeDao.findAll(query, startPage, maxPage);

		List<ThreadTypeData> threadTypeDataList = new ArrayList<ThreadTypeData>();

		dataDb.getData().forEach(threadType -> {
			ThreadTypeData data = new ThreadTypeData();
			data.setId(threadType.getId());
			data.setThreadType(threadType.getThreadType());
			data.setIsActive(threadType.getIsActive());
			data.setVersion(threadType.getVersion());
			threadTypeDataList.add(data);
		});

		SearchQuery<ThreadTypeData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(threadTypeDataList);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = threadTypeDao.deleteById(id);
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
