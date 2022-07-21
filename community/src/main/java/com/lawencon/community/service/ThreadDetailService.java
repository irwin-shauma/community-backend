package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ThreadDetailDao;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threaddetail.ThreadDetailInsertReq;
import com.lawencon.community.model.ThreadDetail;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.User;
import com.lawencon.community.model.EventType;
import com.lawencon.community.model.File;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadDetailService extends BaseCoreService<ThreadDetail> {

	@Autowired
	private ThreadDetailDao threadDetailDao;
	
	@Autowired
	private ThreadHeaderDao threadHeaderDao;
	
	@Autowired
	private UserDao userDao;
	
	public InsertRes insert(ThreadDetailInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			ThreadDetail threadDetail = new ThreadDetail();
			
			ThreadHeader threadHeader = threadHeaderDao.getById(data.getThreadHeaderId());
			threadDetail.setThreadHeader(threadHeader);
			
			User user = userDao.getById(data.getUserId());
			threadDetail.setUser(user);
			threadDetail.setCommentThread(data.getCommentThread());
			
			threadDetail.setIsActive(true);

			begin();
			ThreadDetail threadDetailInsert = save(threadDetail);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(threadDetailInsert.getId());

			result.setData(insertDataRes);
//			result.setMessage(MessageResponse.SAVED.name());	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	
	public UpdateRes update(ThreadDetail data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
//			ThreadDetail threadDetailDb = threadDetailDao.getById(data.getId());
//			
//			ThreadHeader threadHeader = new ThreadHeader();
//			threadHeader.setId(data.getThreadHeader().getId());
//			
//			threadDetailDb.setThreadHeader(threadHeader);
//			
//			File file = new File();
//			file.setId();
//			threadDetailDb.set
//			
//			threadDetailDb.setTitle(data.getTitle());
//			
//			EventType eventDb = eventTypeDao.getById(data.getId());
//			
//			threadDetailDb.setEventTypeId(eventDb);
//
//			threadDetailDb.setIsActive(data.getIsActive());
//			threadDetailDb.setVersion(data.getVersion());
//
//			begin();
//			ThreadDetail threadDetailUpdate = threadDetailDao.save(threadDetailDb);
//			commit();
//
//			UpdateDataRes updateDataRes = new UpdateDataRes();
//
//			updateDataRes.setVersion(threadDetailUpdate.getVersion());
//
//			result.setData(updateDataRes);
//			result.setMessage(MessageResponse.SAVED.name());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	
//	public ThreadDetailFindByIdRes getById(String id) throws Exception {
//		ThreadDetail threadDetailDb = threadDetailDao.getById(id);
//
//		ThreadDetailData data = new ThreadDetailData();
//		data.setId(threadDetailDb.getId());
//		data.setThreadDetailCode(threadDetailDb.getThreadDetailCode());
//		data.setTitle(threadDetailDb.getTitle());
//		data.setEventTypeId(threadDetailDb.getEventType().getId());
//		data.setVersion(threadDetailDb.getVersion());
//
//		ThreadDetailFindByIdRes result = new ThreadDetailFindByIdRes();
//		result.setData(data);
//
//		return result;
//	}
//	
//	
//	public SearchQuery<ThreadDetailData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
//		SearchQuery<ThreadDetail> dataDb = threadDetailDao.findAll(query, startPage, maxPage);
//
//		List<ThreadDetailData> threadDetailDataList = new ArrayList<ThreadDetailData>();
//
//		dataDb.getData().forEach(threadDetail -> {
//			ThreadDetailData data = new ThreadDetailData();
//			data.setId(threadDetail.getId());
//			data.setThreadDetailCode(threadDetail.getThreadDetailCode());
//			data.setTitle(threadDetail.getTitle());
//			data.setEventTypeId(threadDetail.getEventType().getId());
//			data.setVersion(threadDetail.getVersion());
//
//			threadDetailDataList.add(data);
//		});
//
//		SearchQuery<ThreadDetailData> result = new SearchQuery<>();
//		result.setCount(dataDb.getCount());
//		result.setData(threadDetailDataList);
//
//		return result;
//	}
//	
//	public DeleteRes deleteById(String id) throws Exception {
//		DeleteRes result = new DeleteRes();
//
////		result.setMessage(MessageResponse.FAILED.name());
//
//		try {
//			begin();
//			boolean isDeleted = threadDetailDao.deleteById(id);
//			commit();
//
//			if (isDeleted) {
////				result.setMessage(MessageResponse.DELETED.name());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			throw new Exception(e);
//		}
//
//		return result;
//	}
	
}
