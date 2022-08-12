package com.lawencon.community.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.community.constant.MessageResponse;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.PaymentDao;
import com.lawencon.community.dao.PremiumPaymentHistoryDao;
import com.lawencon.community.dao.PremiumTypeDao;
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
import com.lawencon.community.model.File;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.PremiumPaymentHistory;
import com.lawencon.community.model.PremiumType;
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
	
	@Autowired
	private FileDao fileDao;

	public InsertRes insert(PremiumPaymentHistoryInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		String payCode = RandomStringUtils.randomAlphanumeric(5);
		String trxNo = RandomStringUtils.randomAlphanumeric(5);
		try {
			
			begin();
			Payment payment = new Payment();
			
			File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			File insertedFile = fileDao.save(file);
			
			payment.setPaymentCode(payCode);
			
			User users = new User();
			users.setId(getAuthPrincipal());
			payment.setUser(users);
			payment.setFile(insertedFile);
			payment.setIsActive(true);
			payment.setIsApprove(false);
			payment.setCreatedBy(getAuthPrincipal());
			
			Payment insertedPayment = paymentDao.save(payment);
			
			PremiumPaymentHistory premiumPaymentHistory = new PremiumPaymentHistory();
			premiumPaymentHistory.setPayment(insertedPayment);
			premiumPaymentHistory.setPremiumPaymentHistoryCode(code);
			premiumPaymentHistory.setUser(users);
			

			PremiumType premiumType = premiumTypeDao.getById(data.getPremiumTypeId());
			premiumPaymentHistory.setPremiumType(premiumType);

			premiumPaymentHistory.setTrxNo(trxNo);
			premiumPaymentHistory.setIsActive(false);
			
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
			PremiumPaymentHistory premiumPaymentHistoryDb = premiumPaymentHistoryDao.getById(data.getId());
			
			Payment payment = paymentDao.getById(data.getPaymentId());
			payment.setIsApprove(data.getIsApprove());
			
			premiumPaymentHistoryDb.setPayment(payment);
			premiumPaymentHistoryDb.setIsActive(data.getIsActive());
			
			begin();
			paymentDao.save(payment);

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
	
	public PremiumPaymentHistoryFindByIdRes getPremium() throws Exception {
		PremiumPaymentHistory premiumPaymentHistoryDb = premiumPaymentHistoryDao.getPremium(getAuthPrincipal());
		PremiumPaymentHistoryData data = null;
		if(premiumPaymentHistoryDb != null) {
			data = new PremiumPaymentHistoryData();
			data.setId(premiumPaymentHistoryDb.getId());
			data.setUserId(premiumPaymentHistoryDb.getUser().getId());
			data.setPremiumTypeId(premiumPaymentHistoryDb.getPremiumType().getId());
			data.setPaymentId(premiumPaymentHistoryDb.getPayment().getId());
			
			PremiumType premiumType = premiumTypeDao.getById(premiumPaymentHistoryDb.getPremiumType().getId());
			data.setPrice(premiumType.getPrice());
			
			data.setTrxNo(premiumPaymentHistoryDb.getTrxNo());
			data.setIsActive(premiumPaymentHistoryDb.getIsActive());
			data.setVersion(premiumPaymentHistoryDb.getVersion());
		}
		
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
			data.setCreatedAt(premiumPaymentHistory.getCreatedAt());
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
			
			Payment payment = paymentDao.getById(premiumPaymentHistory.getPayment().getId());
			data.setIsAprove(payment.getIsApprove());
			data.setFileId(payment.getFile().getId());
			
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
	
	public SearchQuery<PremiumPaymentHistoryData> findAllUnApprove(String query, Integer startPage, Integer maxPage)
			throws Exception {
		List<PremiumPaymentHistory> dataDb = premiumPaymentHistoryDao.getAllUnAprroved(query, startPage, maxPage);

		List<PremiumPaymentHistoryData> premiumPaymentHistoryDataList = new ArrayList<PremiumPaymentHistoryData>();

		dataDb.forEach(premiumPaymentHistory -> {
			PremiumPaymentHistoryData data = new PremiumPaymentHistoryData();
			data.setId(premiumPaymentHistory.getId());
			data.setUserId(premiumPaymentHistory.getUser().getId());
			data.setPremiumTypeId(premiumPaymentHistory.getPremiumType().getId());
			data.setPaymentId(premiumPaymentHistory.getPayment().getId());
			
			Payment payment = paymentDao.getById(premiumPaymentHistory.getPayment().getId());
			data.setIsAprove(payment.getIsApprove());
			data.setFileId(payment.getFile().getId());
			
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
