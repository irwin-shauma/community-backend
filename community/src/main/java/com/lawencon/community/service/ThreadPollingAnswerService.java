package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ThreadPollingAnswerDao;
import com.lawencon.community.model.ThreadPollingAnswer;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadPollingAnswerService extends BaseCoreService {

	@Autowired
	private ThreadPollingAnswerDao threadPollingAnswerDao;
	
	public ThreadPollingAnswer insert(ThreadPollingAnswer data) throws Exception {
		try {
			begin();
			threadPollingAnswerDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}
	
	
	public ThreadPollingAnswer update(ThreadPollingAnswer data) throws Exception {
		try {
			ThreadPollingAnswer mhsDb = threadPollingAnswerDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			threadPollingAnswerDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public ThreadPollingAnswer getById(String id) throws Exception {
		return threadPollingAnswerDao.getById(id);
	}

	public SearchQuery<ThreadPollingAnswer> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return threadPollingAnswerDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = threadPollingAnswerDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
}
