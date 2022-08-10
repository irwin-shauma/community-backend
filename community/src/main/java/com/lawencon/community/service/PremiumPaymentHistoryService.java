package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.PaymentDao;
import com.lawencon.community.dao.PremiumPaymentHistoryDao;
import com.lawencon.community.dao.PremiumTypeDao;
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.premiumpaymenthistory.PremiumPaymentHistoryData;
import com.lawencon.community.dto.premiumpaymenthistory.PremiumPaymentHistoryFindByIdRes;
import com.lawencon.community.dto.premiumpaymenthistory.PremiumPaymentHistoryInsertReq;
import com.lawencon.community.dto.premiumpaymenthistory.PremiumPaymentHistoryUpdateReq;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.PremiumPaymentHistory;
import com.lawencon.community.model.PremiumType;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class PremiumPaymentHistoryService extends BaseCoreService<PremiumPaymentHistory> {

	@Autowired
	private PremiumPaymentHistoryDao premiumPaymentHistoryDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PremiumTypeDao premiumTypeDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	

	public InsertRes insert(PremiumPaymentHistoryInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		String trxNo = RandomStringUtils.randomAlphanumeric(5);
		try {
			
			PremiumPaymentHistory premiumPaymentHistory = new PremiumPaymentHistory();
			premiumPaymentHistory.setPremiumPaymentHistoryCode(code);
			User user = userDao.getById(getAuthPrincipal());
			premiumPaymentHistory.setUser(user);
			
			Payment payment = paymentDao.getById(data.getPaymentId());
			premiumPaymentHistory.setPayment(payment);

			PremiumType premiumType = premiumTypeDao.getById(data.getPremiumTypeId());
			premiumPaymentHistory.setPremiumType(premiumType);

			premiumPaymentHistory.setTrxNo(trxNo);
			premiumPaymentHistory.setIsActive(true);
			begin();
			PremiumPaymentHistory premiumPaymentHistoryInsert = save(premiumPaymentHistory);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(premiumPaymentHistoryInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(PremiumPaymentHistoryUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			begin();
			PremiumPaymentHistory premiumPaymentHistoryDb = premiumPaymentHistoryDao.getById(data.getId());

			User userDb = userDao.getByIdWithoutDetach(data.getId());
			premiumPaymentHistoryDb.setUser(userDb);
			
			Payment payment = paymentDao.getById(data.getPaymentId());
			premiumPaymentHistoryDb.setPayment(payment);

			PremiumType premiumType = premiumTypeDao.getByIdWithoutDetach(data.getPremiumTypeId());
			premiumPaymentHistoryDb.setPremiumType(premiumType);

			premiumPaymentHistoryDb.setTrxNo(data.getTrxNo());
			premiumPaymentHistoryDb.setIsActive(data.getIsActive());

			PremiumPaymentHistory premiumPaymentHistoryUpdate = premiumPaymentHistoryDao.save(premiumPaymentHistoryDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(premiumPaymentHistoryUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}

	public PremiumPaymentHistoryFindByIdRes getById(String id) throws Exception {
		PremiumPaymentHistory premiumPaymentHistoryDb = premiumPaymentHistoryDao.getById(id);

		PremiumPaymentHistoryData data = new PremiumPaymentHistoryData();
		data.setId(premiumPaymentHistoryDb.getId());
		data.setUserId(premiumPaymentHistoryDb.getUser().getId());
		data.setPremiumTypeId(premiumPaymentHistoryDb.getPremiumType().getId());
		data.setPaymentId(premiumPaymentHistoryDb.getPayment().getId());
		
		PremiumType premiumType = premiumTypeDao.getById(premiumPaymentHistoryDb.getPremiumType().getId());
		data.setPrice(premiumType.getPrice());
		
		data.setTrxNo(premiumPaymentHistoryDb.getTrxNo());
		data.setIsActive(premiumPaymentHistoryDb.getIsActive());
		data.setVersion(premiumPaymentHistoryDb.getVersion());

		PremiumPaymentHistoryFindByIdRes result = new PremiumPaymentHistoryFindByIdRes();
		result.setData(data);

		return result;

	}
	
	public SearchQuery<PremiumPaymentHistoryData> findAllByUser()
			throws Exception {
		List<PremiumPaymentHistory> dataDb = premiumPaymentHistoryDao.getByUser(getAuthPrincipal());

		List<PremiumPaymentHistoryData> premiumPaymentHistoryDataList = new ArrayList<PremiumPaymentHistoryData>();

		dataDb.forEach(premiumPaymentHistory -> {
			PremiumPaymentHistoryData data = new PremiumPaymentHistoryData();
			data.setId(premiumPaymentHistory.getId());
			data.setUserId(premiumPaymentHistory.getUser().getId());
			data.setPremiumTypeId(premiumPaymentHistory.getPremiumType().getId());
			data.setPaymentId(premiumPaymentHistory.getPayment().getId());
			
			User userDb = userDao.getById(premiumPaymentHistory.getUser().getId());
			data.setFullname(userDb.getProfile().getFullName());
			
			PremiumType premiumType = premiumTypeDao.getById(premiumPaymentHistory.getPremiumType().getId());
			data.setPrice(premiumType.getPrice());
			data.setDuration(premiumType.getDuration());
			
			data.setTrxNo(premiumPaymentHistory.getTrxNo());
			data.setIsActive(premiumPaymentHistory.getIsActive());
			data.setVersion(premiumPaymentHistory.getVersion());

			premiumPaymentHistoryDataList.add(data);
		});

		SearchQuery<PremiumPaymentHistoryData> result = new SearchQuery<>();
//		result.setCount(dataDb.getCount());
		result.setData(premiumPaymentHistoryDataList);

		return result;
	}

	public SearchQuery<PremiumPaymentHistoryData> findAll(String query, Integer startPage, Integer maxPage)
			throws Exception {
		SearchQuery<PremiumPaymentHistory> dataDb = premiumPaymentHistoryDao.findAll(query, startPage, maxPage);

		List<PremiumPaymentHistoryData> premiumPaymentHistoryDataList = new ArrayList<PremiumPaymentHistoryData>();

		dataDb.getData().forEach(premiumPaymentHistory -> {
			PremiumPaymentHistoryData data = new PremiumPaymentHistoryData();
			data.setId(premiumPaymentHistory.getId());
			data.setUserId(premiumPaymentHistory.getUser().getId());
			data.setPremiumTypeId(premiumPaymentHistory.getPremiumType().getId());
			data.setPaymentId(premiumPaymentHistory.getPayment().getId());
			
			User userDb = userDao.getById(premiumPaymentHistory.getUser().getId());
			data.setFullname(userDb.getProfile().getFullName());
			
			PremiumType premiumType = premiumTypeDao.getById(premiumPaymentHistory.getPremiumType().getId());
			data.setPrice(premiumType.getPrice());
			data.setDuration(premiumType.getDuration());
			
			data.setTrxNo(premiumPaymentHistory.getTrxNo());
			data.setIsActive(premiumPaymentHistory.getIsActive());
			data.setVersion(premiumPaymentHistory.getVersion());

			premiumPaymentHistoryDataList.add(data);
		});

		SearchQuery<PremiumPaymentHistoryData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(premiumPaymentHistoryDataList);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			boolean isDeleted = premiumPaymentHistoryDao.deleteById(id);
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
