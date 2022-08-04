package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.ThreadHeaderDao;
import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.threadlike.ThreadLikeData;
import com.lawencon.community.dto.threadlike.ThreadLikeFindByIdRes;
import com.lawencon.community.dto.threadlike.ThreadLikeInsertReq;
import com.lawencon.community.dto.threadlike.ThreadLikeUpdateReq;
import com.lawencon.community.model.ThreadHeader;
import com.lawencon.community.model.ThreadLike;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadLikeService extends BaseService<ThreadLike>{
	
	@Autowired
	private ThreadLikeDao threadLikeDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ThreadHeaderDao threadHeaderDao;
	
	public InsertRes insert(ThreadLikeInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			ThreadLike threadLike = new ThreadLike();
			threadLike.setThreadLikeCode(code);
			User user = userDao.getById(getUserId());
			threadLike.setUserId(user);
			
			ThreadHeader threadHeader = threadHeaderDao.getById(data.getThreadId());
			threadLike.setThreadHeader(threadHeader);
			
			threadLike.setIsActive(true);

			begin();
			ThreadLike threadLikeInsert = save(threadLike);
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
	
	
	public UpdateRes update(ThreadLikeUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			ThreadLike threadLikeDb = threadLikeDao.getById(data.getId());

			User userDb = userDao.getById(getUserId());
			threadLikeDb.setUserId(userDb);
			
			ThreadHeader threadHeader = threadHeaderDao.getById(data.getId());
			threadLikeDb.setThreadHeader(threadHeader);

			threadLikeDb.setIsActive(data.getIsActive());
			threadLikeDb.setVersion(data.getVersion());

			begin();
			ThreadLike threadLikeUpdate = threadLikeDao.save(threadLikeDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(threadLikeUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	public ThreadLikeFindByIdRes getById(String id) throws Exception {
		ThreadLike threadLikeDb = threadLikeDao.getById(id);

		ThreadLikeData data = new ThreadLikeData();
		data.setId(threadLikeDb.getId());
		data.setThreadLikeCode(threadLikeDb.getThreadLikeCode());
		data.setUserId(threadLikeDb.getUserId().getId());
		data.setThreadId(threadLikeDb.getThreadHeader().getId());
		data.setIsActive(threadLikeDb.getIsActive());
		data.setVersion(threadLikeDb.getVersion());

		ThreadLikeFindByIdRes result = new ThreadLikeFindByIdRes();
		result.setData(data);

		return result;
	}

	
	public SearchQuery<ThreadLikeData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadLike> dataDb = threadLikeDao.findAll(query, startPage, maxPage);

		List<ThreadLikeData> threadLikeDataList = new ArrayList<ThreadLikeData>();

		dataDb.getData().forEach(threadLike -> {
			ThreadLikeData data = new ThreadLikeData();
			data.setId(threadLike.getId());
			data.setThreadLikeCode(threadLike.getThreadLikeCode());
			data.setUserId(threadLike.getUserId().getId());
			data.setThreadId(threadLike.getThreadHeader().getId());
			data.setIsActive(threadLike.getIsActive());
			data.setVersion(threadLike.getVersion());
			
			data.setVersion(threadLike.getVersion());

			threadLikeDataList.add(data);
		});

		SearchQuery<ThreadLikeData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(threadLikeDataList);

		return result;
	}
	
	public SearchQuery<ThreadLikeData> findAllByThread(String id, Integer startPage, Integer maxPage) throws Exception {
		List<ThreadLike> likes = threadLikeDao.findByThread(id, startPage, maxPage);
		int count = threadLikeDao.countLikes(id).intValue();
		List<ThreadLikeData> result = new ArrayList<>();
		
		likes.forEach(like -> {
			ThreadLikeData data = new ThreadLikeData();
			data.setId(like.getId());
			data.setUserId(like.getUserId().getId());
			data.setThreadId(like.getThreadHeader().getId());
			
			result.add(data);
		});
		SearchQuery<ThreadLikeData> res = new SearchQuery<>();
		res.setCount(count);
		res.setData(result);
		return res;
	}
	
	public ThreadLikeFindByIdRes getByThreadAndUser(String id) throws Exception {
		ThreadLike threadLikeDb = threadLikeDao.findByThreadAndUser(id, getUserId());

		ThreadLikeData data = new ThreadLikeData();
		data.setId(threadLikeDb.getId());
		data.setThreadLikeCode(threadLikeDb.getThreadLikeCode());
		data.setUserId(threadLikeDb.getUserId().getId());
		data.setThreadId(threadLikeDb.getThreadHeader().getId());
		data.setIsActive(threadLikeDb.getIsActive());
		data.setVersion(threadLikeDb.getVersion());

		ThreadLikeFindByIdRes result = new ThreadLikeFindByIdRes();
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
			threadLikeDao.deleteByThreadAndUser(thread, getUserId());
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
}
