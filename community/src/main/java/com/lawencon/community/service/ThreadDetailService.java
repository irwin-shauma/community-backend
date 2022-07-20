package com.lawencon.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.ThreadDetailDao;
import com.lawencon.community.model.ThreadDetail;
import com.lawencon.model.SearchQuery;

@Service
public class ThreadDetailService extends BaseCoreService {

	@Autowired
	private ThreadDetailDao threadDetailDao;
	
	public ThreadDetail findByUserId(String userId) throws Exception {
		try {
			ThreadDetail threadDetail = threadDetailDao.findByUserId(userId);
			return threadDetail;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public ThreadDetail insert(ThreadDetail data) throws Exception {
		try {
			begin();
			threadDetailDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return data;
	}
	
	
	public ThreadDetail update(ThreadDetail data) throws Exception {
		try {
			ThreadDetail mhsDb = threadDetailDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());
			
			begin();
			threadDetailDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return data;
	}

	public ThreadDetail getById(String id) throws Exception {
		return threadDetailDao.getById(id);
	}

	public SearchQuery<ThreadDetail> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return threadDetailDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = threadDetailDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
}
