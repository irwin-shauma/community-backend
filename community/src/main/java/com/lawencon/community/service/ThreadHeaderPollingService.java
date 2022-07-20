package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ThreadHeaderPollingDao;
import com.lawencon.community.model.ThreadHeaderPolling;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadHeaderPollingService extends BaseCoreService {
	
	@Autowired
	private ThreadHeaderPollingDao threadHeaderPollingDao;
	
	public ThreadHeaderPolling insert(ThreadHeaderPolling data) throws Exception {
		try {
			begin();
			threadHeaderPollingDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}
	
	
	public ThreadHeaderPolling update(ThreadHeaderPolling data) throws Exception {
		try {
			ThreadHeaderPolling mhsDb = threadHeaderPollingDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			threadHeaderPollingDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public ThreadHeaderPolling getById(String id) throws Exception {
		return threadHeaderPollingDao.getById(id);
	}

	public SearchQuery<ThreadHeaderPolling> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return threadHeaderPollingDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = threadHeaderPollingDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}

}
