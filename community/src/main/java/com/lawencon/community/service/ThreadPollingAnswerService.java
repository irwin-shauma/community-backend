package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.ThreadHeaderPollingDao;
import com.lawencon.community.dao.ThreadPollingAnswerDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threadpollinganswer.ThreadPollingAnswerData;
import com.lawencon.community.dto.threadpollinganswer.ThreadPollingAnswerFindByIdRes;
import com.lawencon.community.dto.threadpollinganswer.ThreadPollingAnswerInsertReq;
import com.lawencon.community.dto.threadpollinganswer.ThreadPollingAnswerUpdateReq;
import com.lawencon.community.model.ThreadHeaderPolling;
import com.lawencon.community.model.ThreadPollingAnswer;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadPollingAnswerService extends BaseCoreService<ThreadPollingAnswer> {

	@Autowired
	private ThreadPollingAnswerDao threadPollingAnswerDao;
	
	@Autowired
	private UserDao userDao;
	
	private ThreadHeaderPollingDao threadHeaderPollingDao;
	
	public InsertRes insert(ThreadPollingAnswerInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			ThreadPollingAnswer threadPollingAnswer = new ThreadPollingAnswer();
			
			ThreadHeaderPolling threadHeaderPolling = new ThreadHeaderPolling();
			threadHeaderPolling.setId(data.getThreadPollingId());
			threadPollingAnswer.setThreadHeaderPolling(threadHeaderPolling);
			
			User user = new User();
			user.setId(data.getUserId());
			threadPollingAnswer.setUser(user);

			threadPollingAnswer.setIsActive(true);

			begin();
			ThreadPollingAnswer threadPollingAnswerInsert = save(threadPollingAnswer);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(threadPollingAnswerInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.name());	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	
	public UpdateRes update(ThreadPollingAnswerUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			ThreadPollingAnswer threadPollingAnswerDb = threadPollingAnswerDao.getById(data.getId());

			ThreadHeaderPolling threadHeaderPollingDb = threadHeaderPollingDao.getById(data.getId());
			threadPollingAnswerDb.setThreadHeaderPolling(threadHeaderPollingDb);
			
			User userDb = userDao.getById(data.getId());
			threadPollingAnswerDb.setUser(userDb);

			threadPollingAnswerDb.setIsActive(data.getIsActive());
			threadPollingAnswerDb.setVersion(data.getVersion());

			begin();
			ThreadPollingAnswer threadPollingAnswerUpdate = threadPollingAnswerDao.save(threadPollingAnswerDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(threadPollingAnswerUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.name());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	
	public ThreadPollingAnswerFindByIdRes getById(String id) throws Exception {
		ThreadPollingAnswer threadPollingAnswerDb = threadPollingAnswerDao.getById(id);

		ThreadPollingAnswerData data = new ThreadPollingAnswerData();
		data.setId(threadPollingAnswerDb.getId());
		data.setThreadPollingAnswerCode(threadPollingAnswerDb.getThreadPollingAnswerCode());
		data.setUserId(threadPollingAnswerDb.getUser().getId());
		data.setThreadPollingId(threadPollingAnswerDb.getThreadHeaderPolling().getId());
		data.setIsActive(threadPollingAnswerDb.getIsActive());
		data.setVersion(threadPollingAnswerDb.getVersion());

		ThreadPollingAnswerFindByIdRes result = new ThreadPollingAnswerFindByIdRes();
		result.setData(data);

		return result;
	}
	
	public SearchQuery<ThreadPollingAnswerData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadPollingAnswer> dataDb = threadPollingAnswerDao.findAll(query, startPage, maxPage);

		List<ThreadPollingAnswerData> threadPollingAnswerDataList = new ArrayList<ThreadPollingAnswerData>();

		dataDb.getData().forEach(threadPollingAnswer -> {
			ThreadPollingAnswerData data = new ThreadPollingAnswerData();
			data.setId(threadPollingAnswer.getId());
			data.setThreadPollingAnswerCode(threadPollingAnswer.getThreadPollingAnswerCode());
			data.setUserId(threadPollingAnswer.getUser().getId());
			data.setThreadPollingId(threadPollingAnswer.getThreadHeaderPolling().getId());
			data.setIsActive(threadPollingAnswer.getIsActive());
			data.setVersion(threadPollingAnswer.getVersion());
			threadPollingAnswerDataList.add(data);
		});

		SearchQuery<ThreadPollingAnswerData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(threadPollingAnswerDataList);

		return result;
	}
	
	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.name());

		try {
			begin();
			boolean isDeleted = threadPollingAnswerDao.deleteById(id);
			commit();

			if (isDeleted) {
				result.setMessage(MessageResponse.DELETED.name());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
}
