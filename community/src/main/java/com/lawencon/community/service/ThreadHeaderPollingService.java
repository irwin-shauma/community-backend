package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

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
import com.lawencon.community.dto.threadheaderpolling.ThreadHeaderPollingData;
import com.lawencon.community.dto.threadheaderpolling.ThreadHeaderPollingFindByIdRes;
import com.lawencon.community.dto.threadheaderpolling.ThreadHeaderPollingInsertReq;
import com.lawencon.community.dto.threadheaderpolling.ThreadHeaderPollingUpdateReq;
import com.lawencon.community.dto.threadheaderpolling.ThreadPollingDetailData;
import com.lawencon.community.model.ThreadHeaderPolling;
import com.lawencon.community.model.ThreadPollingDetail;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadHeaderPollingService extends BaseCoreService<ThreadHeaderPolling> {

	@Autowired
	private ThreadHeaderPollingDao threadHeaderPollingDao;

	@Autowired
	private ThreadPollingDetailDao pollingDetailDao;

	public InsertRes insert(ThreadHeaderPollingInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {

			ThreadHeaderPolling threadHeaderPollingInsert = new ThreadHeaderPolling();
			threadHeaderPollingInsert.setTitlePolling(data.getTitlePolling());
			threadHeaderPollingInsert.setContentPolling(data.getContentPolling());
			threadHeaderPollingInsert.setIsActive(true);

			begin();
			ThreadHeaderPolling threadHeaderPollingResult = save(threadHeaderPollingInsert);
			for (int i = 0; i < data.getThreadPollingDetail().size(); i++) {
				ThreadPollingDetail threadDtl = new ThreadPollingDetail();
				threadDtl.setThreadHeaderPolling(threadHeaderPollingResult);
				threadDtl.setQuestion(data.getThreadPollingDetail().get(i).getQuestion());
				threadDtl.setIsActive(true);
				threadDtl.setCreatedBy(getAuthPrincipal());

				pollingDetailDao.save(threadDtl);
			}
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(threadHeaderPollingResult.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(ThreadHeaderPollingUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			ThreadHeaderPolling threadHeaderPollingInsert = threadHeaderPollingDao.getById(data.getId());
			threadHeaderPollingInsert.setTitlePolling(data.getTitlePolling());
			threadHeaderPollingInsert.setContentPolling(data.getContentPolling());
			threadHeaderPollingInsert.setIsActive(true);

			begin();
			ThreadHeaderPolling threadDetailPollingResult = threadHeaderPollingDao.save(threadHeaderPollingInsert);
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

	public ThreadHeaderPollingFindByIdRes getById(String id) throws Exception {
		ThreadHeaderPolling threadDetailDb = threadHeaderPollingDao.getById(id);

		ThreadHeaderPollingData data = new ThreadHeaderPollingData();
		data.setId(threadDetailDb.getId());
		data.setTitlePolling(threadDetailDb.getTitlePolling());
		data.setContentPolling(threadDetailDb.getContentPolling());
		data.setIsActive(threadDetailDb.getIsActive());
		data.setVersion(threadDetailDb.getVersion());

		ThreadHeaderPollingFindByIdRes result = new ThreadHeaderPollingFindByIdRes();
		result.setData(data);

		return result;
	}

	public SearchQuery<ThreadHeaderPollingData> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<ThreadHeaderPolling> dataDb = threadHeaderPollingDao.findAll(query, startPage, maxPage);

		List<ThreadHeaderPollingData> threadHeaderPollings = new ArrayList<ThreadHeaderPollingData>();
		List<ThreadPollingDetailData> threadDtlPollings = new ArrayList<ThreadPollingDetailData>();
		dataDb.getData().forEach(threadHeader -> {
			ThreadHeaderPollingData data = new ThreadHeaderPollingData();
			data.setId(threadHeader.getId());
			data.setTitlePolling(threadHeader.getTitlePolling());
			data.setContentPolling(threadHeader.getContentPolling());
			data.setIsActive(threadHeader.getIsActive());
			data.setVersion(threadHeader.getVersion());

			try {
				List<ThreadPollingDetail> threadDtl = pollingDetailDao.findByHeader(threadHeader.getId());
				for (int i = 0; i < threadDtl.size(); i++) {
					ThreadPollingDetailData threadDtlPolling = new ThreadPollingDetailData();
					threadDtlPolling.setId(threadDtl.get(i).getId());
					threadDtlPolling.setQuestion(threadDtl.get(i).getQuestion());
					threadDtlPolling.setIsActive(threadDtl.get(i).getIsActive());
					threadDtlPolling.setVersion(threadDtl.get(i).getVersion());

					threadDtlPollings.add(threadDtlPolling);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			data.setThreadDtlPolling(threadDtlPollings);
			threadHeaderPollings.add(data);
		});

		SearchQuery<ThreadHeaderPollingData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(threadHeaderPollings);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = threadHeaderPollingDao.deleteById(id);
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
