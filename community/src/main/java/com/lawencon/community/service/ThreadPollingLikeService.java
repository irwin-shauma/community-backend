package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.ThreadHeaderPollingDao;
import com.lawencon.community.dao.ThreadPollingLikeDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threadpollinglike.ThreadPollingLikeData;
import com.lawencon.community.dto.threadpollinglike.ThreadPollingLikeFindByIdRes;
import com.lawencon.community.dto.threadpollinglike.ThreadPollingLikeInsertReq;
import com.lawencon.community.dto.threadpollinglike.ThreadPollingLikeUpdateReq;
import com.lawencon.community.model.ThreadHeaderPolling;
import com.lawencon.community.model.ThreadPollingLike;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadPollingLikeService extends BaseCoreService<ThreadPollingLike>{
	
	@Autowired
	private ThreadPollingLikeDao threadLikeDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ThreadHeaderPollingDao threadHeaderPollingDao;
	
	public InsertRes insert(ThreadPollingLikeInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			ThreadPollingLike threadLike = new ThreadPollingLike();
			threadLike.setThreadPollingLikeCode(code);
			User user = userDao.getById(getAuthPrincipal());
			threadLike.setUserId(user);
			
			ThreadHeaderPolling threadHeaderPolling = threadHeaderPollingDao.getById(data.getThreadPollingHeaderId());
			threadLike.setThreadHeaderPolling(threadHeaderPolling);
			
			threadLike.setIsActive(true);

			begin();
			ThreadPollingLike threadLikeInsert = save(threadLike);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(threadLikeInsert.getId());

			result.setData(insertDataRes);	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	
	public UpdateRes update(ThreadPollingLikeUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			ThreadPollingLike threadLikeDb = threadLikeDao.getById(data.getId());

			User userDb = userDao.getById(getAuthPrincipal());
			threadLikeDb.setUserId(userDb);
			
			ThreadHeaderPolling threadHeaderPolling = threadHeaderPollingDao.getById(data.getId());
			threadLikeDb.setThreadHeaderPolling(threadHeaderPolling);

			threadLikeDb.setIsActive(data.getIsActive());
			threadLikeDb.setVersion(data.getVersion());

			begin();
			ThreadPollingLike threadLikeUpdate = threadLikeDao.save(threadLikeDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(threadLikeUpdate.getVersion());

			result.setData(updateDataRes);

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	public ThreadPollingLikeFindByIdRes getById(String id) throws Exception {
		ThreadPollingLike threadLikeDb = threadLikeDao.getById(id);

		ThreadPollingLikeData data = new ThreadPollingLikeData();
		data.setId(threadLikeDb.getId());
		data.setThreadPollingLikeCode(threadLikeDb.getThreadPollingLikeCode());
		data.setUserId(threadLikeDb.getUserId().getId());
		data.setThreadPollingHeaderId(threadLikeDb.getThreadHeaderPolling().getId());
		data.setIsActive(threadLikeDb.getIsActive());
		data.setVersion(threadLikeDb.getVersion());

		ThreadPollingLikeFindByIdRes result = new ThreadPollingLikeFindByIdRes();
		result.setData(data);

		return result;
	}

	
	public SearchQuery<ThreadPollingLikeData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadPollingLike> dataDb = threadLikeDao.findAll(query, startPage, maxPage);

		List<ThreadPollingLikeData> threadLikeDataList = new ArrayList<ThreadPollingLikeData>();

		dataDb.getData().forEach(threadLike -> {
			ThreadPollingLikeData data = new ThreadPollingLikeData();
			data.setId(threadLike.getId());
			data.setThreadPollingLikeCode(threadLike.getThreadPollingLikeCode());
			data.setUserId(threadLike.getUserId().getId());
			data.setThreadPollingHeaderId(threadLike.getThreadHeaderPolling().getId());
			data.setIsActive(threadLike.getIsActive());
			data.setVersion(threadLike.getVersion());
			
			data.setVersion(threadLike.getVersion());

			threadLikeDataList.add(data);
		});

		SearchQuery<ThreadPollingLikeData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(threadLikeDataList);

		return result;
	}
	
	public SearchQuery<ThreadPollingLikeData> findAllByThread(String id, Integer startPage, Integer maxPage) throws Exception {
		List<ThreadPollingLike> likes = threadLikeDao.findByThread(id, startPage, maxPage);
		int count = threadLikeDao.countLikes(id).intValue();
		List<ThreadPollingLikeData> result = new ArrayList<>();
		
		likes.forEach(like -> {
			ThreadPollingLikeData data = new ThreadPollingLikeData();
			data.setId(like.getId());
			data.setUserId(like.getUserId().getId());
			data.setThreadPollingHeaderId(like.getThreadHeaderPolling().getId());
			
			result.add(data);
		});
		SearchQuery<ThreadPollingLikeData> res = new SearchQuery<>();
		res.setCount(count);
		res.setData(result);
		return res;
	}
	
	public ThreadPollingLikeFindByIdRes getByThreadAndUser(String id) throws Exception {
		ThreadPollingLike threadLikeDb = threadLikeDao.findByThreadAndUser(id, getAuthPrincipal());

		ThreadPollingLikeData data = new ThreadPollingLikeData();
		data.setId(threadLikeDb.getId());
		data.setThreadPollingLikeCode(threadLikeDb.getThreadPollingLikeCode());
		data.setUserId(threadLikeDb.getUserId().getId());
		data.setThreadPollingHeaderId(threadLikeDb.getThreadHeaderPolling().getId());
		data.setIsActive(threadLikeDb.getIsActive());
		data.setVersion(threadLikeDb.getVersion());

		ThreadPollingLikeFindByIdRes result = new ThreadPollingLikeFindByIdRes();
		result.setData(data);

		return result;
	}
	
	
	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = threadLikeDao.deleteById(id);
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
	
	public DeleteRes deleteByThreadAndUser(String thread) throws Exception {
		DeleteRes result = new DeleteRes();

		try {
			begin();
			threadLikeDao.deleteByThreadAndUser(thread, getAuthPrincipal());
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
}
