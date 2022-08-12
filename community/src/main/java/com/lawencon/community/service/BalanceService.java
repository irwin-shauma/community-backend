package com.lawencon.community.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
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
import com.lawencon.community.dto.balance.PremiumUpdateBalanceReq;
import com.lawencon.community.dto.balance.UpdateCurrentBalanceReq;
import com.lawencon.community.dto.balance.UpdateCurrentBalanceRes;
import com.lawencon.community.model.Balance;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class BalanceService extends BaseCoreService<Balance> {

	@Autowired
	private BalanceDao balanceDao;

	@Autowired
	private UserDao userDao;

	public InsertRes insert(BalanceInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			begin();
			Balance balance = new Balance();
			balance.setBalanceCode(code);
			balance.setCurrentBalance(data.getCurrentBalance());

			User user = userDao.getById(data.getUserId());
			balance.setUser(user);

			balance.setIsActive(true);

			Balance balanceInsert = save(balance);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(balanceInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
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
			begin();
			Balance balanceDb = balanceDao.getByIdWithoutDetach(data.getId());
			balanceDb.setCurrentBalance(data.getCurrentBalance());

			User userDb = userDao.getByIdWithoutDetach(data.getId());

			balanceDb.setUser(userDb);

			balanceDb.setIsActive(data.getIsActive());

			Balance balanceUpdate = balanceDao.save(balanceDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(balanceUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());

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
		data.setIsActive(balanceDb.getIsActive());
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
			data.setIsActive(balance.getIsActive());
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
		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = balanceDao.deleteById(id);
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

	public BalanceFindByIdRes getByUserId() throws Exception {
		Balance balanceDb = balanceDao.findByUserId(getAuthPrincipal());

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

	public UpdateCurrentBalanceRes updateBalance(UpdateCurrentBalanceReq data) throws Exception {
		UpdateCurrentBalanceRes response = new UpdateCurrentBalanceRes();

		try {
			begin();
			Balance balance = balanceDao.findByUserId(data.getUserId());
			BigDecimal balanceUpdated = balance.getCurrentBalance()
					.add(data.getBalance().multiply(new BigDecimal(0.9d)));
			balance.setCurrentBalance(balanceUpdated);
			balance.setUpdatedBy(getAuthPrincipal());
			Balance updated = balanceDao.save(balance);

			Balance systemBalance = balanceDao.findSystem();
			BigDecimal systemBalanceIncrement = systemBalance.getCurrentBalance()
					.add(data.getBalance().multiply(new BigDecimal(0.1d)));
			systemBalance.setCurrentBalance(systemBalanceIncrement);
			systemBalance.setUpdatedBy(getAuthPrincipal());
			balanceDao.save(systemBalance);
			commit();

			response.setMessage("Update balance success! Current balance : " + updated.getCurrentBalance());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return response;
	}
	
	public UpdateCurrentBalanceRes premiumUpdateBalance(PremiumUpdateBalanceReq data) throws Exception {
		UpdateCurrentBalanceRes response = new UpdateCurrentBalanceRes();

		try {
			begin();

			Balance systemBalance = balanceDao.findSystem();
			BigDecimal systemBalanceIncrement = systemBalance.getCurrentBalance()
					.add(data.getBalance());
			systemBalance.setCurrentBalance(systemBalanceIncrement);
			balanceDao.save(systemBalance);
			commit();

			response.setMessage("Update balance success!");

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return response;
	}

}
