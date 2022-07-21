package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.dao.BalanceDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.balance.BalanceData;
import com.lawencon.community.dto.balance.BalanceFindByIdRes;
import com.lawencon.community.dto.balance.BalanceInsertReq;
import com.lawencon.community.dto.balance.BalanceUpdateReq;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class BalanceService extends BaseCoreService<Balance> {

	private BalanceDao balanceDao;
	private UserDao userDao;

	public InsertRes insert(BalanceInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			Balance balance = new Balance();
			balance.setBalanceCode(data.getBalanceCode());
			balance.setCurrentBalance(data.getCurrentBalance());

			User user = new User();
			user.setId(data.getUserId());

			balance.setUser(user);

			balance.setIsActive(true);

			begin();
			Balance balanceInsert = save(balance);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(balanceInsert.getId());

			result.setData(insertDataRes);
//			result.setMessage(MessageResponse.SAVED.name());	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(BalanceUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			Balance balanceDb = balanceDao.getById(data.getId());
			balanceDb.setBalanceCode(data.getBalanceCode());
			balanceDb.setCurrentBalance(data.getCurrentBalance());

			User userDb = userDao.getById(data.getId());

			balanceDb.setUser(userDb);

			balanceDb.setIsActive(data.getIsActive());
			balanceDb.setVersion(data.getVersion());

			begin();
			Balance balanceUpdate = balanceDao.save(balanceDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(balanceUpdate.getVersion());

			result.setData(updateDataRes);
//			result.setMessage(MessageResponse.SAVED.name());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public BalanceFindByIdRes getById(String id) throws Exception {
		Balance balanceDb = balanceDao.getById(id);

		BalanceData data = new BalanceData();
		data.setId(balanceDb.getId());
		data.setBalanceCode(balanceDb.getBalanceCode());
		data.setCurrentBalance(balanceDb.getCurrentBalance());
		data.setUserId(balanceDb.getUser().getId());
		data.setVersion(balanceDb.getVersion());

		BalanceFindByIdRes result = new BalanceFindByIdRes();
		result.setData(data);

		return result;
	}

	public SearchQuery<BalanceData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Balance> dataDb = balanceDao.findAll(query, startPage, maxPage);

		List<BalanceData> balanceDataList = new ArrayList<BalanceData>();

		dataDb.getData().forEach(balance -> {
			BalanceData data = new BalanceData();
			data.setId(balance.getId());
			data.setBalanceCode(balance.getBalanceCode());
			data.setCurrentBalance(balance.getCurrentBalance());
			data.setUserId(balance.getUser().getId());
			
			data.setVersion(balance.getVersion());

			balanceDataList.add(data);
		});

		SearchQuery<BalanceData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(balanceDataList);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

//		result.setMessage(MessageResponse.FAILED.name());

		try {
			begin();
			boolean isDeleted = balanceDao.deleteById(id);
			commit();

			if (isDeleted) {
//				result.setMessage(MessageResponse.DELETED.name());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

}
