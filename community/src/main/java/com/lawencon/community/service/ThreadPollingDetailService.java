package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ThreadPollingDetailDao;
import com.lawencon.community.model.ThreadPollingDetail;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadPollingDetailService extends BaseCoreService {
	
	@Autowired
	private ThreadPollingDetailDao threadPollingDetailDao;

	public ThreadPollingDetail insert(ThreadPollingDetail data) throws Exception {
		try {
			begin();
			threadPollingDetailDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}
	
	
	public ThreadPollingDetail update(ThreadPollingDetail data) throws Exception {
		try {
			ThreadPollingDetail mhsDb = threadPollingDetailDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			threadPollingDetailDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public ThreadPollingDetail getById(String id) throws Exception {
		return threadPollingDetailDao.getById(id);
	}

	public SearchQuery<ThreadPollingDetail> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return threadPollingDetailDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = threadPollingDetailDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
}
