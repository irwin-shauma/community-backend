package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.ThreadHeaderPollingDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threaddetail.ThreadDetailData;
import com.lawencon.community.dto.threaddetail.ThreadDetailFindByIdRes;
import com.lawencon.community.dto.threaddetail.ThreadDetailInsertReq;
import com.lawencon.community.dto.threaddetail.ThreadDetailUpdateReq;
import com.lawencon.community.model.File;
import com.lawencon.community.model.ThreadDetail;
import com.lawencon.community.model.ThreadHeaderPolling;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadHeaderPollingService extends BaseCoreService<ThreadHeaderPolling>{
	
//	@Autowired
//	private ThreadHeaderPollingDao threadHeaderPollingDao;
//	
//	public InsertRes insert(ThreadDetailInsertReq data) throws Exception {
//		InsertRes result = new InsertRes();
//		try {
//			
//			ThreadHeaderPolling threadHeaderPollingInsert = new ThreadHeaderPolling();
//			threadHeaderPollingInsert.set
//			
//			threadDetail.setCommentThread(data.getCommentThread());
//			
//			threadDetail.setIsActive(true);
//
//			begin();
//			ThreadHeaderPolling threadHeaderPollingResult = save(threadHeaderPollingInsert);
//			commit();
//
//			InsertDataRes insertDataRes = new InsertDataRes();
//			insertDataRes.setId(threadDetailInsert.getId());
//
//			result.setData(insertDataRes);
//			result.setMessage(MessageResponse.SAVED.name());	
//		} catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			throw new Exception(e);
//		}
//
//		return result;
//	}
//	
//	
//	public UpdateRes update(ThreadDetailUpdateReq data) throws Exception {
//		UpdateRes result = new UpdateRes();
//
//		try {
//			ThreadDetail threadDetailInsert = threadDetailDao.getById(data.getId());
//			
//			ThreadHeaderPolling threadHeaderPolling = ThreadHeaderPollingDao.getById(data.getThreadHeaderPollingId());
//			threadDetailInsert.setThreadHeaderPolling(threadHeaderPolling);
//			
//			File file = fileDao.getById(data.getFileId());
//			threadDetailInsert.setFile(file);
//			
//			User user = userDao.getById(data.getUserId());
//			threadDetailInsert.setUser(user);
//			
//			threadDetailInsert.setCommentThread(data.getCommentThread());
//			threadDetailInsert.setIsActive(true);
//			threadDetailInsert.setVersion(data.getVersion());
//
//			begin();
//			ThreadDetail threadDetailUpdate = threadDetailDao.save(threadDetailInsert);
//			commit();
//
//			UpdateDataRes updateDataRes = new UpdateDataRes();
//
//			updateDataRes.setVersion(threadDetailUpdate.getVersion());
//
//			result.setData(updateDataRes);
//			result.setMessage(MessageResponse.SAVED.name());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			throw new Exception(e);
//		}
//		return result;
//	}
//	
//	
//	public ThreadDetailFindByIdRes getById(String id) throws Exception {
//		ThreadDetail threadDetailDb = threadDetailDao.getById(id);
//
//		ThreadDetailData data = new ThreadDetailData();
//		data.setId(threadDetailDb.getId());
//		data.setThreadHeaderPollingId(threadDetailDb.getThreadHeaderPolling().getId());
//		data.setFileId(threadDetailDb.getFile().getId());
//		data.setUserId(threadDetailDb.getUser().getId());
//		data.setCommentThread(threadDetailDb.getCommentThread());
//		data.setIsActive(threadDetailDb.getIsActive());
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
//			data.setThreadHeaderPollingId(threadDetail.getThreadHeaderPolling().getId());
//			data.setFileId(threadDetail.getFile().getId());
//			data.setUserId(threadDetail.getUser().getId());
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
//		result.setMessage(MessageResponse.FAILED.name());
//
//		try {
//			begin();
//			boolean isDeleted = threadDetailDao.deleteById(id);
//			commit();
//
//			if (isDeleted) {
//				result.setMessage(MessageResponse.DELETED.name());
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
