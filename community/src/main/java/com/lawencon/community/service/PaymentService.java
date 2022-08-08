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
import com.lawencon.community.dao.ProfileDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.DeleteRes;
import com.lawencon.community.dto.InsertDataRes;
import com.lawencon.community.dto.InsertRes;
import com.lawencon.community.dto.UpdateDataRes;
import com.lawencon.community.dto.UpdateRes;
import com.lawencon.community.dto.payment.PaymentData;
import com.lawencon.community.dto.payment.PaymentFindByIdRes;
import com.lawencon.community.dto.payment.PaymentInsertReq;
import com.lawencon.community.dto.payment.PaymentUpdateReq;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Payment;
import com.lawencon.community.model.Profile;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Service
public class PaymentService extends BaseCoreService<Payment>{
	
	@Autowired
	private PaymentDao paymentDao;
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private FileDao fileDao;
	
	public InsertRes insert(PaymentInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		String code = RandomStringUtils.randomAlphanumeric(5);
		try {
			begin();
			Payment payment = new Payment();
			payment.setPaymentCode(code);
			User user = userDao.getById(getAuthPrincipal());
			payment.setUser(user);

			if(data.getFileName()!= null) {
				File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				fileDao.save(file);
				payment.setFile(file);
			}
			
			payment.setIsApprove(false);
			payment.setIsActive(true);
			
			Payment paymentInsert = save(payment);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(paymentInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.getMessageResponse());	
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}
	
	
	public UpdateRes update(PaymentUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();

		try {
			Payment paymentDb = paymentDao.getById(data.getId());

			paymentDb.setIsApprove(data.getIsApprove());

			begin();
			Payment paymentUpdate = paymentDao.save(paymentDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();

			updateDataRes.setVersion(paymentUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.UPDATED.getMessageResponse());

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		return result;
	}
	
	public PaymentFindByIdRes getById(String id) throws Exception {
		Payment paymentDb = paymentDao.getById(id);

		PaymentData data = new PaymentData();
		data.setId(paymentDb.getId());
		data.setPaymentCode(paymentDb.getPaymentCode());
		data.setUserId(paymentDb.getUser().getId());
		
		User user = userDao.getById(paymentDb.getUser().getId());
		Profile profile = profileDao.getById(user.getProfile().getId());
		data.setFullName(profile.getFullName());
		
		data.setFileId(paymentDb.getFile().getId());
		data.setIsActive(paymentDb.getIsActive());
		data.setVersion(paymentDb.getVersion());

		PaymentFindByIdRes result = new PaymentFindByIdRes();
		result.setData(data);

		return result;
	}
	
	
	public SearchQuery<PaymentData> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Payment> dataDb = paymentDao.findAll(query, startPage, maxPage);

		List<PaymentData> paymentDataList = new ArrayList<PaymentData>();

		dataDb.getData().forEach(payment -> {
			PaymentData data = new PaymentData();
			data.setId(payment.getId());
			data.setPaymentCode(payment.getPaymentCode());
			data.setUserId(payment.getUser().getId());
			
			User user = userDao.getById(payment.getUser().getId());
			Profile profile = profileDao.getById(user.getProfile().getId());
			data.setFullName(profile.getFullName());
			data.setFileId(payment.getFile().getId());
			data.setIsActive(payment.getIsActive());
			data.setVersion(payment.getVersion());
			paymentDataList.add(data);
		});

		SearchQuery<PaymentData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(paymentDataList);

		return result;
	}
	
	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();

		result.setMessage(MessageResponse.FAILED.getMessageResponse());

		try {
			begin();
			Payment payment = paymentDao.getById(id);
			if(payment.getFile() != null) {
				fileDao.deleteById(payment.getFile().getId());
			}
			
			boolean isDeleted = paymentDao.deleteById(id);
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
