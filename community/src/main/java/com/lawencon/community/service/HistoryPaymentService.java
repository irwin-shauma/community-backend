package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.HistoryPaymentDao;
import com.lawencon.community.model.HistoryPayment;
import com.lawencon.model.SearchQuery;

public class HistoryPaymentService extends BaseCoreService {

	@Autowired
	private HistoryPaymentDao historyPaymentDao;

	public HistoryPayment insert(HistoryPayment data) throws Exception {
		try {
			begin();
			historyPaymentDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public HistoryPayment update(HistoryPayment data) throws Exception {
		try {
			HistoryPayment mhsDb = historyPaymentDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			historyPaymentDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public HistoryPayment getById(String id) throws Exception {
		return historyPaymentDao.getById(id);
	}

	public SearchQuery<HistoryPayment> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return historyPaymentDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = historyPaymentDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	
	public List<HistoryPayment> findAllByUserId(String userId) throws Exception{
		List<HistoryPayment> historyPayment = new ArrayList<HistoryPayment>();
		try {
			historyPayment = historyPaymentDao.findAllByUserId(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return historyPayment;
	}

}
