package com.lawencon.community.service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.BalanceDao;
import com.lawencon.community.model.Balance;
import com.lawencon.model.SearchQuery;

public class BalanceService extends BaseCoreService<Balance>{
	
	private BalanceDao balanceDao;
	
	public Balance insert(Balance data) throws Exception {
		try {
			begin();
			balanceDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}
	
	
	public Balance update(Balance data) throws Exception {
		try {
			Balance mhsDb = balanceDao.getById(data.getId());
			data.setCreatedAt(mhsDb.getCreatedAt());
			data.setCreatedBy(mhsDb.getCreatedBy());

			begin();
			balanceDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return data;
	}

	public Balance getById(String id) throws Exception {
		return balanceDao.getById(id);
	}

	public SearchQuery<Balance> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		return balanceDao.findAll(query, startPage, maxPage);
	}

	public boolean deleteById(String id) throws Exception {
		try {
			begin();
			boolean isDeleted = balanceDao.deleteById(id);
			commit();

			return isDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	}
	
	public Balance findByUserId(String userId) throws Exception{
		Balance balance = new Balance();
		try {
			balance = balanceDao.findByUserId(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	
	
	

}
