package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.HistoryPaymentDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.historypayment.HistoryPaymentData;
import com.lawencon.community.dto.historypayment.HistoryPaymentFindByIdRes;
import com.lawencon.community.dto.historypayment.HistoryPaymentInsertReq;
import com.lawencon.community.dto.historypayment.HistoryPaymentUpdateReq;
import com.lawencon.community.model.HistoryPayment;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class HistoryPaymentService extends BaseCoreService<HistoryPayment> {

	@Autowired
	private HistoryPaymentDao historyPaymentDao;
	
	@Autowired
	private UserDao userDao;
	
	public InsertRes insert(HistoryPaymentInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			HistoryPayment historyPayment = new HistoryPayment();
			historyPayment.setHistoryPaymentCode(code);
			historyPayment.setHistoryPaymentCode(data.getHistoryPaymentCode());
			
			User user = userDao.getById(data.getUserId());
			historyPayment.setUser(user);
			
			historyPayment.setTrxNo(data.getTrxNo());
			historyPayment.setIsActive(true);

			begin();
			HistoryPayment historyPaymentInsert = save(historyPayment);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(historyPaymentInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	public UpdateRes update(HistoryPaymentUpdateReq data) throws Exception{
		UpdateRes result = new UpdateRes();
		
		try {
			HistoryPayment historyPaymentDb = historyPaymentDao.getById(data.getId());
			historyPaymentDb.setHistoryPaymentCode(data.getHistoryPaymentCode());
			historyPaymentDb.setTrxNo(data.getTrxNo());
			
			User userDb = userDao.getById(data.getId());
			
			historyPaymentDb.setUser(userDb);
			
			historyPaymentDb.setIsActive(data.getIsActive());
			historyPaymentDb.setVersion(data.getVersion());
			
			begin();
			HistoryPayment historyPaymentUpdate = historyPaymentDao.save(historyPaymentDb);
			commit();
			
			UpdateDataRes updateDataRes = new UpdateDataRes();
			
			updateDataRes.setVersion(historyPaymentUpdate.getVersion());
			
			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());
		}catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	public HistoryPaymentFindByIdRes getById(String id) throws Exception{
		HistoryPayment historyPaymentDb = historyPaymentDao.getById(id);
		
		HistoryPaymentData data = new HistoryPaymentData();
		data.setId(historyPaymentDb.getId());
		data.setHistoryPaymentCode(historyPaymentDb.getHistoryPaymentCode());
		data.setTrxNo(historyPaymentDb.getTrxNo());
		data.setUserId(historyPaymentDb.getUser().getId());
		data.setIsActive(historyPaymentDb.getIsActive());
		data.setVersion(historyPaymentDb.getVersion());
		
		HistoryPaymentFindByIdRes result = new HistoryPaymentFindByIdRes();
		result.setData(data);
		
		return result;
		
	}
	
	public SearchQuery<HistoryPaymentData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<HistoryPayment> dataDb = historyPaymentDao.findAll(query, startPage, maxPage);
		
		List<HistoryPaymentData> historyPaymentDataList = new ArrayList<HistoryPaymentData>();
		
		dataDb.getData().forEach(historyPayment -> {
			HistoryPaymentData data = new HistoryPaymentData();
			data.setId(historyPayment.getId());
			data.setHistoryPaymentCode(historyPayment.getHistoryPaymentCode());
			data.setTrxNo(historyPayment.getTrxNo());
			data.setUserId(historyPayment.getUser().getId());
			data.setIsActive(historyPayment.getIsActive());
			data.setVersion(historyPayment.getVersion());
			
			historyPaymentDataList.add(data);
		});
		
		SearchQuery<HistoryPaymentData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(historyPaymentDataList);
		
		return result;
	}
	
	
	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = historyPaymentDao.deleteById(id);
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
	
	
}
