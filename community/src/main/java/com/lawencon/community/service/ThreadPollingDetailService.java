package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.ThreadHeaderPollingDao;
import com.lawencon.community.dao.ThreadPollingDetailDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threadpollingdetail.ThreadPollingDetailData;
import com.lawencon.community.dto.threadpollingdetail.ThreadPollingDetailFindByIdRes;
import com.lawencon.community.dto.threadpollingdetail.ThreadPollingDetailInsertReq;
import com.lawencon.community.dto.threadpollingdetail.ThreadPollingDetailUpdateReq;
import com.lawencon.community.model.ThreadHeaderPolling;
import com.lawencon.community.model.ThreadPollingDetail;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadPollingDetailService extends BaseCoreService<ThreadPollingDetail> {
	
	@Autowired
	private ThreadPollingDetailDao threadPollingDetailDao;
	
	@Autowired
	private ThreadHeaderPollingDao threadHeaderPollingDao;

	public InsertRes insert(ThreadPollingDetailInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {

			ThreadPollingDetail threadPollingDetailInsert = new ThreadPollingDetail();
			threadPollingDetailInsert.setThreadPollingDetailCode(code);
			ThreadHeaderPolling threadHeaderPolling = threadHeaderPollingDao.getById(data.getThreadHeaderPollingId());
			
			threadPollingDetailInsert.setThreadHeaderPolling(threadHeaderPolling);
			threadPollingDetailInsert.setPollingChoice(data.getPollingChoice());
			threadPollingDetailInsert.setIsActive(true);

			begin();
			ThreadPollingDetail threadPollingDetailResult = save(threadPollingDetailInsert);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(threadPollingDetailResult.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(ThreadPollingDetailUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			ThreadPollingDetail threadPollingDetailInsert = threadPollingDetailDao.getById(data.getId());
			
			ThreadHeaderPolling threadHeaderPolling = threadHeaderPollingDao.getById(data.getThreadHeaderPollingId());
			
			threadPollingDetailInsert.setThreadHeaderPolling(threadHeaderPolling);
			threadPollingDetailInsert.setPollingChoice(data.getPollingChoice());
			threadPollingDetailInsert.setIsActive(true);

			begin();
			ThreadPollingDetail threadDetailPollingResult = threadPollingDetailDao.save(threadPollingDetailInsert);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();
			updateDataRes.setVersion(threadDetailPollingResult.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public ThreadPollingDetailFindByIdRes getById(String id) throws Exception {
		ThreadPollingDetail threadDetailDb = threadPollingDetailDao.getById(id);

		ThreadPollingDetailData data = new ThreadPollingDetailData();
		data.setId(threadDetailDb.getId());
		data.setThreadHeaderPollingId(threadDetailDb.getThreadHeaderPolling().getId());
		data.setQuestion(threadDetailDb.getPollingChoice());
		data.setIsActive(threadDetailDb.getIsActive());
		data.setVersion(threadDetailDb.getVersion());

		ThreadPollingDetailFindByIdRes result = new ThreadPollingDetailFindByIdRes();
		result.setData(data);

		return result;
	}

	public SearchQuery<ThreadPollingDetailData> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<ThreadPollingDetail> dataDb = threadPollingDetailDao.findAll(query, startPage, maxPage);

		List<ThreadPollingDetailData> threadPollingDetails = new ArrayList<ThreadPollingDetailData>();

		dataDb.getData().forEach(threadHeader -> {
			ThreadPollingDetailData data = new ThreadPollingDetailData();
			data.setId(threadHeader.getId());
			data.setThreadHeaderPollingId(threadHeader.getThreadHeaderPolling().getId());
			data.setQuestion(threadHeader.getPollingChoice());
			data.setIsActive(threadHeader.getIsActive());
			data.setVersion(threadHeader.getVersion());

			threadPollingDetails.add(data);
		});

		SearchQuery<ThreadPollingDetailData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(threadPollingDetails);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = threadPollingDetailDao.deleteById(id);
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
